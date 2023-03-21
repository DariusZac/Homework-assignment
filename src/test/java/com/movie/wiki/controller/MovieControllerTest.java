package com.movie.wiki.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.wiki.business.service.MovieService;
import com.movie.wiki.model.ActorNMovie;
import com.movie.wiki.model.MovieDto;
import com.movie.wiki.model.TopMovies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    MovieService service;

    private MovieDto movieDto = new MovieDto();
    private ActorNMovie actorNMovie = new ActorNMovie();
    private TopMovies topMovies = new TopMovies(1L, "Title", 7, 1);

    @BeforeEach
    void setUp() {
        movieDto.setId(1L);
        movieDto.setName("Title");
        actorNMovie.setId(1L);
        actorNMovie.setName("Title");
    }

    @Test
    void getAllMovies() throws Exception {
        when(service.getAllMovies()).thenReturn(List.of(movieDto));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/"))
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(List.of(movieDto))))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void addMovie() throws Exception {
        when(service.addMovie(any(MovieDto.class))).thenReturn(movieDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/")
                        .content(new ObjectMapper().writeValueAsString(movieDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(movieDto)));
    }

    @Test
    void updateMovie() throws Exception {
        when(service.updateMovie(any(MovieDto.class))).thenReturn(movieDto);
        mockMvc.perform(MockMvcRequestBuilders.patch("/")
                        .content(new ObjectMapper().writeValueAsString(movieDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(movieDto)));
    }

    @Test
    void deleteMovie() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void addActor_failure() throws Exception {
        when(service.addActor(anyLong(), anyLong())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/actor")
                        .param("movieId", "1")
                        .param("actorId", "1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void addActor_Success() throws Exception {
        when(service.addActor(anyLong(), anyLong())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/actor")
                        .param("movieId", "1")
                        .param("actorId", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getMovieActors() throws Exception {
        when(service.getMovieActors(anyLong())).thenReturn(actorNMovie);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/1/actors"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(actorNMovie)));
    }

    @Test
    void getTopRatedMovies() throws Exception {
        when(service.getTopRatedMovies()).thenReturn(List.of(topMovies));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/top"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(List.of(topMovies))));
    }
}