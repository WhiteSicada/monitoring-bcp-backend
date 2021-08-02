package com.bcp.monitoring.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.bcp.monitoring.dto.EquipeDto;
import com.bcp.monitoring.service.EquipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EquipeController.class})
@ExtendWith(SpringExtension.class)
public class EquipeControllerTest {
    @Autowired
    private EquipeController equipeController;

    @MockBean
    private EquipeService equipeService;

    @Test
    public void testCreateEquipe() throws Exception {
        EquipeDto equipeDto = new EquipeDto();
        equipeDto.setId(123L);
        equipeDto.setName("Name");
        when(this.equipeService.createEquipe((EquipeDto) any())).thenReturn(equipeDto);

        EquipeDto equipeDto1 = new EquipeDto();
        equipeDto1.setId(123L);
        equipeDto1.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(equipeDto1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/equipe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.equipeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":123,\"name\":\"Name\"}"));
    }

    @Test
    public void testUpdateEquipe() throws Exception {
        EquipeDto equipeDto = new EquipeDto();
        equipeDto.setId(123L);
        equipeDto.setName("Name");
        when(this.equipeService.updateEquipe((Long) any(), (EquipeDto) any())).thenReturn(equipeDto);

        EquipeDto equipeDto1 = new EquipeDto();
        equipeDto1.setId(123L);
        equipeDto1.setName("Name");
        String content = (new ObjectMapper()).writeValueAsString(equipeDto1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/equipe/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.equipeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":123,\"name\":\"Name\"}"));
    }

    @Test
    public void testDeleteEquipe() throws Exception {
        when(this.equipeService.deleteEquipe((Long) any())).thenReturn("foo");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/equipe/{id}", 123L);
        MockMvcBuilders.standaloneSetup(this.equipeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("foo"));
    }

    @Test
    public void testDeleteEquipe2() throws Exception {
        when(this.equipeService.deleteEquipe((Long) any())).thenReturn("foo");
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/equipe/{id}", 123L);
        deleteResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.equipeController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("foo"));
    }

    @Test
    public void testGetAllEquipes() throws Exception {
        when(this.equipeService.getEquipeList()).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/equipes");
        MockMvcBuilders.standaloneSetup(this.equipeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetAllEquipes2() throws Exception {
        when(this.equipeService.getEquipeList()).thenReturn(null);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/equipes");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.equipeController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

