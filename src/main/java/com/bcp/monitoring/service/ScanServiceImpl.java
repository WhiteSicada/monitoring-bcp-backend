package com.bcp.monitoring.service;

import com.bcp.monitoring.convertor.ScanConvertor;
import com.bcp.monitoring.dto.scan.ScanDtoShow;
import com.bcp.monitoring.email.MailRequest;
import com.bcp.monitoring.email.MailResponse;
import com.bcp.monitoring.model.*;
import com.bcp.monitoring.repository.ApiRepository;
import com.bcp.monitoring.repository.ProjetRepository;
import com.bcp.monitoring.repository.ScanRepository;
import com.bcp.monitoring.repository.TestRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


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

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private ProjetRepository projetRepository;

    @Autowired
    private Configuration config;

    Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Override
    public List<ScanDtoShow> scanTest(Long id) {
        Optional<Test> test = testRepository.findById(id);
        List<Api> apiList = test.get().getListAPIs();
        List<ScanDtoShow> scanDtoShows = new ArrayList<>();
        List<String> listErrorMessagesForEmail = new ArrayList<String>();
        List<Anomalie> anomalies = new ArrayList<Anomalie>();
//        for (Api api : apiList) {
//            List<Endpoint> endpointList = api.getEndpoints();
//            for (Endpoint endpoint : endpointList) {
//                try {
//                    Scan savedScan = makeRequest(test.get(), api, endpoint, listErrorMessagesForEmail,anomalies);
//                    scanDtoShows.add(scanConvertor.entityToDoto(savedScan));
//                } catch (Exception exception) {
//                    System.out.println(exception.getMessage());
//                }
//            }
//        }
//        for (Api api : apiList) {
//            for(Anomalie anomalie : anomalies){
//                api.addAnomalie(anomalie);
//                apiRepository.save(api);
//            }
//        }

//        if (!listErrorMessagesForEmail.isEmpty()) {
//            List<Api> listAPIs = test.get().getListAPIs();
//            for (Api api : listAPIs) {
//                //send email
////                Projet projet = projetRepository.findByApis(api);
//                MailRequest requestIt = new MailRequest("Adnane", "mohamedadnane.drissilahsini@uit.ac.ma", "mohamed.dl.adnane@gmail.com", "Monitoring App : Scans erros.");
////                MailRequest requestWork = new MailRequest("Adnane", projet.getResponsableIt().getEmail(), "mohamed.dl.adnane@gmail.com", "Monitoring App : Scans erros.");
//                Map<String, Object> model = new HashMap<>();
////                for (int i = 0; i < listErrorMessagesForEmail.size(); i++) {
////                    model.put("error" + i, listErrorMessagesForEmail.get(i));
////                }
//                model.put("listErrors",listErrorMessagesForEmail);
//                sendEmail(requestIt,model);
////                sendEmail(requestWork,model);
//            }
//        }

        return scanDtoShows;
    }

    @Override
    public List<ScanDtoShow> getAllScans() {
        List<Scan> scanList = scanRepository.findAllByOrderByIdDesc();
        return scanConvertor.entitiesToDotos(scanList);
    }

    public String formatEndpointForUrl(Api api, Endpoint endpoint) {
        // re construct
//        return "http://" + api.getIp() + ":" + api.getPort() + "/" + api.getContext() + "/" + endpoint.getUrl();
        return "http://" + api.getIp() + ":" + api.getPort() + "/" ;
    }

    public String createCustomErrorTextForEmail(Api api, Test test, String url, String date , String errorText) {
        return "The " + api.getName() + " API has encountred an error when scanning the endpoint "
                + url + " of the test " + test.getName() + " at " + date + " with error message : " + errorText;
    }

    public Scan executeRequest(ResponseEntity<String> response, String responseTime, Api api, Test test,
                               String url, HttpEntity request, Long time, HttpMethod callType, List<String> listErrorMessagesForEmail,List<Anomalie> anomalies) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Scan savedScan;
        try {
            response = new RestTemplate().exchange(url, callType, request, String.class);
            responseTime = String.valueOf((System.currentTimeMillis() - time));
            Scan scan = new Scan(api.getName(), test.getName(), "Completed", "Automatically", "Successful", responseTime, formatter.format(date), "GET", url);
            savedScan = scanRepository.save(scan);
        } catch (Exception exception) {
            // make request
            responseTime = String.valueOf((System.currentTimeMillis() - time));
            // save scan failed
            Scan scan = new Scan(api.getName(), test.getName(), "Not Completed", "Automatically", "UnSuccessful", responseTime, formatter.format(date), "GET", url);
            savedScan = scanRepository.save(scan);
            // create anomalie
            Anomalie anomalie = new Anomalie(exception.getMessage(), url, formatter.format(date));
            anomalies.add(anomalie);
            listErrorMessagesForEmail.add(createCustomErrorTextForEmail(api, test, url, formatter.format(date),exception.getMessage()));
        }
        return savedScan;
    }

    // function to make a request
    public Scan makeRequest(Test test, Api api, Endpoint endpoint, List<String> listErrorMessagesForEmail,List<Anomalie> anomalies) {
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
                savedScan = executeRequest(response, responseTime, api, test, url, request, time, HttpMethod.GET, listErrorMessagesForEmail,anomalies);
                break;
            case "POST":
                time = System.currentTimeMillis();
                request = new HttpEntity(endpoint.getData(), headers);
                savedScan = executeRequest(response, responseTime, api, test, url, request, time, HttpMethod.POST, listErrorMessagesForEmail,anomalies);
                break;
            case "PUT":
                time = System.currentTimeMillis();
                request = new HttpEntity(headers);
                savedScan = executeRequest(response, responseTime, api, test, url, request, time, HttpMethod.PUT, listErrorMessagesForEmail,anomalies);
                break;
            case "DELETE":
                time = System.currentTimeMillis();
                request = new HttpEntity(headers);
                savedScan = executeRequest(response, responseTime, api, test, url, request, time, HttpMethod.DELETE, listErrorMessagesForEmail,anomalies);
                break;
        }
        return savedScan;
    }

    public MailResponse sendEmail(MailRequest request, Map<String, Object> model) {
        MailResponse response = new MailResponse();
        MimeMessage message = sender.createMimeMessage();
        try {
            // set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Template t = config.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            helper.setTo(request.getTo());
            helper.setText(html, true);
            helper.setSubject(request.getSubject());
            helper.setFrom(request.getFrom());
            sender.send(message);

            response.setMessage("mail send to : " + request.getTo());
            response.setStatus(Boolean.TRUE);

        } catch (MessagingException | IOException | TemplateException e) {
            response.setMessage("Mail Sending failure : " + e.getMessage());
            response.setStatus(Boolean.FALSE);
        }

        return response;
    }


}
