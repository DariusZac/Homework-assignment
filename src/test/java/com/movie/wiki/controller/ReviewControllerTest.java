package com.movie.wiki.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.movie.wiki.business.service.ReviewService;
import com.movie.wiki.model.ReviewDto;
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
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    ReviewService service;

    private final String URL = "/reviews";
    private ReviewDto reviewDto = new ReviewDto();
    private ObjectMapper objectMapper = new ObjectMapper();
    private String stringObject;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        reviewDto.setReviewer("Greg");
        reviewDto.setId(1L);
        reviewDto.setScore(10);
        reviewDto.setReview("meh");
        reviewDto.setDate(LocalDate.parse("2022-02-02"));
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        stringObject = objectMapper.writeValueAsString(reviewDto);
    }

    @Test
    void getAllReviewsFromMovie() throws Exception {
        when(service.getReviewsFromMovie(anyLong())).thenReturn(List.of(reviewDto));
        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/1"))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(List.of(reviewDto))))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteReview() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void addReview() throws Exception {
        when(service.addReview(anyLong(), any(ReviewDto.class))).thenReturn(reviewDto);
        mockMvc.perform(MockMvcRequestBuilders.post(URL + "/1")
                        .content(stringObject).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(stringObject));
    }

    @Test
    void updateReview() throws Exception {
        when(service.updateReview(any(ReviewDto.class))).thenReturn(reviewDto);
        mockMvc.perform(MockMvcRequestBuilders.patch(URL)
                        .content(stringObject).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(stringObject));
    }
}