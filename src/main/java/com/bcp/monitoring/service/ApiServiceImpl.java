package com.bcp.monitoring.service;

import com.bcp.monitoring.convertor.ApiConvertor;
import com.bcp.monitoring.dto.api.ApiDto;
import com.bcp.monitoring.dto.api.ApiDtoShow;
import com.bcp.monitoring.model.Api;
import com.bcp.monitoring.repository.ApiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    public ApiConvertor apiConvertor;

    @Autowired
    public ApiRepository apiRepository;

    Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Transactional
    @Override
    public ApiDtoShow createApi(ApiDto apiDto) {
        Api api = new Api();
        boolean formatingCompleted = apiConvertor.dtoToEntity(apiDto, api);
        logger.info(String.valueOf(apiDto));
        if (formatingCompleted) {
            // check If the api is availbele
//            String url = api.getIp() + ":" + api.getPort();
//            if (checkAPiExists(url)) {
//                //save
//                apiRepository.save(api);
//                return apiConvertor.entityToDto(api);
//            }
//            return null;
            apiRepository.save(api);
            return apiConvertor.entityToDto(api);
        }
        return null;
    }

    @Override
    public List<ApiDtoShow> getAllApis() {
        List<Api> apiList = apiRepository.findAll();
        return apiConvertor.entitiesToDtos(apiList);
    }

    @Transactional
    @Override
    public ApiDtoShow updateApi(Long id, ApiDto apiDto) {
        Optional<Api> api = apiRepository.findById(id);
        if (api.isPresent()) {
            boolean formatingCompleted = apiConvertor.dtoToEntity(apiDto, api.get());
            if (formatingCompleted) {
                apiRepository.save(api.get());
                return apiConvertor.entityToDto(api.get());
            } else {
                return null;
            }
        }
        return null;
    }

    @Override
    public String deleteApi(Long id) {
        Optional<Api> api = apiRepository.findById(id);
        if (api.isPresent()) {
            apiRepository.deleteById(api.get().getId());
            return "Api with id: " + id + " deleted successfully!";
        }
        return "Api with id: " + id + " not deleted successfully!";
    }

    @Override
    public Boolean checkAPiExists(String url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            final String uri = "http://" + url + "/actuator/health";
            String result = restTemplate.getForObject(uri, String.class);
            System.out.println(result);
        } catch (HttpStatusCodeException ex) {
            // raw http status code e.g `404`
            System.out.println(ex.getRawStatusCode());
            // http status code e.g. `404 NOT_FOUND`
            System.out.println(ex.getStatusCode().toString());
            // get response body
            System.out.println(ex.getResponseBodyAsString());
            // get http headers
            HttpHeaders headers= ex.getResponseHeaders();
            System.out.println(headers.get("Content-Type"));
            System.out.println(headers.get("Server"));
        }
        return true;
    }
}
