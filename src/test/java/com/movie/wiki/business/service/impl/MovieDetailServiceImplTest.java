package com.movie.wiki.business.service.impl;

import com.movie.wiki.business.mapper.MovieDetailMapper;
import com.movie.wiki.business.repository.MovieDetailRepository;
import com.movie.wiki.business.repository.MovieRepository;
import com.movie.wiki.business.repository.model.Movie;
import com.movie.wiki.business.repository.model.MovieDetail;
import com.movie.wiki.exception.IdNotFound;
import com.movie.wiki.model.MovieDetailDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class MovieDetailServiceImplTest {

    @Mock
    private MovieDetailRepository repository;
    @Mock
    private MovieDetailMapper mapper;
    @Mock
    private MovieRepository movieRepository;
    @InjectMocks
    private MovieDetailServiceImpl service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getDetails_Positive() {
        MovieDetail detail = new MovieDetail();
        MovieDetailDto dto = new MovieDetailDto();
        when(repository.findById(anyLong())).thenReturn(Optional.of(detail));
        when(mapper.entityToDto(any(MovieDetail.class))).thenReturn(dto);
        Assertions.assertEquals(dto, service.getDetails(1L));
    }

    @Test
    void getDetails_BadIdGiven() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Throwable error = catchThrowable(() -> service.getDetails(0L));
        assertThat(error)
                .isInstanceOf(IdNotFound.class)
                .hasMessage("No details with id: 0");
    }

    @Test
    void addDetails_Successful() {
        Movie movie = new Movie();
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movie));
        MovieDetail movieDetail = new MovieDetail();
        when(mapper.dtoToEntity(any(MovieDetailDto.class))).thenReturn(movieDetail);
        when(repository.save(any(MovieDetail.class))).thenReturn(movieDetail);
        MovieDetailDto dto = new MovieDetailDto();
        dto.setId(1L);
        when(mapper.entityToDto(any(MovieDetail.class))).thenReturn(dto);
        Assertions.assertEquals(dto, service.addDetails(dto));
    }

    @Test
    void addDetails_NoMovieWithSuchId() {
        MovieDetailDto dto = new MovieDetailDto();
        dto.setId(0L);
        when(movieRepository.findById(anyLong())).thenReturn(Optional.empty());
        Throwable error = catchThrowable(() -> service.addDetails(dto));
        assertThat(error)
                .isInstanceOf(IdNotFound.class)
                .hasMessage("No movie with id: 0");
    }
}