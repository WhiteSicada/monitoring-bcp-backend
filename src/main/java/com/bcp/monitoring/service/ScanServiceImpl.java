package com.bcp.monitoring.service;

import com.bcp.monitoring.convertor.ScanConvertor;
import com.bcp.monitoring.dto.scan.ScanDtoShow;
import com.bcp.monitoring.model.*;
import com.bcp.monitoring.repository.AnomalieRepository;
import com.bcp.monitoring.repository.ApiRepository;
import com.bcp.monitoring.repository.ScanRepository;
import com.bcp.monitoring.repository.TestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ScanServiceImpl implements ScanSerive {

    @Autowired
    public ScanRepository scanRepository;

    @Autowired
    public ScanConvertor scanConvertor;

    @Autowired
    public TestRepository testRepository;

    @Autowired
    public ApiRepository apiRepository;

    Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Override
    public List<ScanDtoShow> scanTest(Long id) {
        Optional<Test> test = testRepository.findById(id);
        List<ScanDtoShow> scanDtoShows = new ArrayList<>();
        for (Api api : test.get().getListAPIs()) {

            for (Endpoint endpoint : api.getEndpoints()) {

                try {
                    Scan savedScan = makeRequest(test.get(), api, endpoint);
                    scanDtoShows.add(scanConvertor.entityToDoto(savedScan));
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        }
        return scanDtoShows;
    }

    @Override
    public List<ScanDtoShow> getAllScans() {
        List<Scan> scanList = scanRepository.findAll();
        return scanConvertor.entitiesToDotos(scanList);
    }

    public String formatEndpointForUrl(Api api, Endpoint endpoint) {
        return "http://" + api.getIp() + ":" + api.getPort() + "/" + api.getContext() + "/" + endpoint.getUrl();
    }

    public Scan executeRequest(ResponseEntity<String> response, String responseTime, Api api, Test test, Endpoint endpoint, String url, HttpEntity request, Long time, HttpMethod callType) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Scan savedScan;
        try {
            response = new RestTemplate().exchange(url, callType, request, String.class);
            responseTime = String.valueOf((System.currentTimeMillis() - time));
            Scan scan = new Scan(api, test, endpoint, "Completed", "Automatically", "Successful", responseTime, formatter.format(date), "GET", url);
            savedScan = scanRepository.save(scan);
        } catch (Exception exception) {
            responseTime = String.valueOf((System.currentTimeMillis() - time));
            Scan scan = new Scan(api, test, endpoint, "Not Completed", "Automatically", "UnSuccessful", responseTime, formatter.format(date), "GET", url);
            savedScan = scanRepository.save(scan);
            Anomalie anomalie = new Anomalie(exception.getMessage(),url, formatter.format(date));
            api.addAnomalie(anomalie);
            apiRepository.save(api);
            //send email
        }
        return savedScan;
    }

    // function to make a request
    public Scan makeRequest(Test test, Api api, Endpoint endpoint) {
        String url = formatEndpointForUrl(api, endpoint);
        logger.warn(url);
        ResponseEntity<String> response = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", api.getToken());
        String responseTime = null;
        Scan savedScan = null;

        HttpEntity request;
        long time;
        switch (endpoint.getMethod()) {
            case "GET":
                time = System.currentTimeMillis();
                request = new HttpEntity(headers);
                savedScan = executeRequest(response, responseTime, api, test, endpoint, url, request, time, HttpMethod.GET);
                break;
            case "POST":
                time = System.currentTimeMillis();
                request = new HttpEntity(endpoint.getData(), headers);
                savedScan = executeRequest(response, responseTime, api, test, endpoint, url, request, time, HttpMethod.POST);
                break;
            case "PUT":
                time = System.currentTimeMillis();
                request = new HttpEntity(headers);
                savedScan = executeRequest(response, responseTime, api, test, endpoint, url, request, time, HttpMethod.PUT);
                break;
            case "DELETE":
                time = System.currentTimeMillis();
                request = new HttpEntity(headers);
                savedScan = executeRequest(response, responseTime, api, test, endpoint, url, request, time, HttpMethod.DELETE);
                break;
        }
        return savedScan;
    }


}
