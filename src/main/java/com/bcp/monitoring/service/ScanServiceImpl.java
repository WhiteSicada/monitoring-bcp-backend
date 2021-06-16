package com.bcp.monitoring.service;

import com.bcp.monitoring.convertor.ScanConvertor;
import com.bcp.monitoring.dto.scan.ScanDtoShow;
import com.bcp.monitoring.model.Api;
import com.bcp.monitoring.model.Endpoint;
import com.bcp.monitoring.model.Scan;
import com.bcp.monitoring.model.Test;
import com.bcp.monitoring.repository.ScanRepository;
import com.bcp.monitoring.repository.TestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Override
    public List<ScanDtoShow> scanTest(Long id) {
        Optional<Test> test = testRepository.findById(id);
        List<ScanDtoShow> scanDtoShows = new ArrayList<>();
        for (Api api : test.get().getListAPIs()) {

            for (Endpoint endpoint : api.getEndpoints()) {
                Scan scan = new Scan();
                boolean request = makeRequest(test.get(), api, endpoint, scan);
                if (request) {
                    Scan savedScan = scanRepository.save(scan);
                    scanDtoShows.add(scanConvertor.entityToDoto(savedScan));
                } else {
                    logger.warn("ddddddddddd");
                    //create anomalie
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

    // function to make a request
    public boolean makeRequest(Test test, Api api, Endpoint endpoint, Scan scan) {
        String url = "http://" + api.getIp() + ":" + api.getPort() + "/" + api.getContext() + "/" + endpoint.getUrl();
        logger.warn(url);
        ResponseEntity<String> response;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", api.getToken());
        String responseTime;
        HttpEntity request;
        long time;
        switch (endpoint.getMethod()) {
            case "GET":
                time = System.currentTimeMillis();
                scan.setMethod("GET");
                request = new HttpEntity(headers);
                response = new RestTemplate().exchange(url, HttpMethod.GET, request, String.class);
                responseTime = String.valueOf((System.currentTimeMillis() - time));
                break;
            case "POST":
                time = System.currentTimeMillis();
                scan.setMethod("POST");
                request = new HttpEntity(endpoint.getData(),headers);
                response = new RestTemplate().exchange(url, HttpMethod.POST, request, String.class);
                responseTime = String.valueOf((System.currentTimeMillis() - time));
                break;
            case "PUT":
                scan.setMethod("PUT");
                time = System.currentTimeMillis();
                request = new HttpEntity(headers);
                response = new RestTemplate().exchange(url, HttpMethod.PUT, request, String.class);
                responseTime = String.valueOf((System.currentTimeMillis() - time));
                break;
            case "DELETE":
                scan.setMethod("DELETE");
                time = System.currentTimeMillis();
                request = new HttpEntity(headers);
                response = new RestTemplate().exchange(url, HttpMethod.DELETE, request, String.class);
                responseTime = String.valueOf((System.currentTimeMillis() - time));
                break;
            default:
                scan.setMethod("GET");
                time = System.currentTimeMillis();
                request = new HttpEntity(headers);
                response = new RestTemplate().exchange(url, HttpMethod.GET, request, String.class);
                responseTime = String.valueOf((System.currentTimeMillis() - time));
        }
        responseTime = String.valueOf((System.currentTimeMillis() - time));
        scan.setApi(api);
        scan.setTest(test);
        scan.setUrl(url);
        scan.setEndpoint(endpoint);
        scan.setExecution_time(responseTime);
        if (!response.getStatusCode().toString().equals("200 OK")) {
            scan.setStatus("Not Completed");
            scan.setSuccessful("UnSuccessful");
            return false;
        } else {
            scan.setStatus("Completed");
            scan.setSuccessful("Successful");
        }
        scan.setSpark("Automatically");

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        scan.setCreates_at(formatter.format(date));
        return true;
    }


}
