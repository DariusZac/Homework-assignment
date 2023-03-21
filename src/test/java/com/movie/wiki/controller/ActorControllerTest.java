package com.movie.wiki.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.movie.wiki.business.service.ActorService;
import com.movie.wiki.model.ActorDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@WebMvcTest(ActorController.class)
class ActorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ActorService service;

    private final String URL = "/actors";
    private ActorDto actorDto = new ActorDto();
    private ObjectMapper objectMapper = new ObjectMapper();
    private String stringObject;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        actorDto.setId(1L);
        actorDto.setFullName("Greg");
        actorDto.setNationality("test");
        actorDto.setDate(LocalDate.parse("1980-09-09"));
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        stringObject = objectMapper.writeValueAsString(actorDto);
    }

    @Test
    void getAllActors() throws Exception {
        when(service.getAllActors()).thenReturn(List.of(actorDto));
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(List.of(actorDto))));
    }

    @Test
    void addActor() throws Exception {
        when(service.addActor(any(ActorDto.class))).thenReturn(actorDto);
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .content(stringObject).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(stringObject))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteActor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}