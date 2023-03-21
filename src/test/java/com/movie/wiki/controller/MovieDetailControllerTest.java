package com.movie.wiki.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.movie.wiki.business.service.MovieDetailService;
import com.movie.wiki.model.MovieDetailDto;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@WebMvcTest(MovieDetailController.class)
class MovieDetailControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    MovieDetailService service;

    private final String URL = "/details";

    private MovieDetailDto movieDetail = new MovieDetailDto();
    private ObjectMapper objectMapper = new ObjectMapper();
    private String stringObject;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        movieDetail.setId(1L);
        movieDetail.setDate(LocalDate.parse("1994-01-01"));
        movieDetail.setDescription("description");
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        stringObject = objectMapper.writeValueAsString(movieDetail);
    }

    @Test
    void getDetails() throws Exception {
        when(service.getDetails(anyLong())).thenReturn(movieDetail);
        mockMvc.perform(MockMvcRequestBuilders
                        .get(URL + "/0"))
                .andExpect(MockMvcResultMatchers
                        .content().json(stringObject))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void addDetails() throws Exception {
        when(service.addDetails(any(MovieDetailDto.class))).thenReturn(movieDetail);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(URL).content(stringObject)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers
                        .content().json(stringObject))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}