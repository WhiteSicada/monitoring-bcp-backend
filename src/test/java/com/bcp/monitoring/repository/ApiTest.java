package com.bcp.monitoring.repository;

import com.bcp.monitoring.model.Api;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class ApiTest {

    @Autowired
    ApiRepository apiRepository;

    @Test
    void findByName(){
        Optional<Api> api = apiRepository.findByName("Evolan");
        assertEquals("Evolan",api.get().getName());
    }

    @Test
    void deleteById(){
        apiRepository.deleteById(98523L);
        assertNull(apiRepository.findById(98523L));
    }
}
