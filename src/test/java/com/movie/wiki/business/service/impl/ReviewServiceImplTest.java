package com.movie.wiki.business.service.impl;

import com.movie.wiki.business.mapper.ReviewMapper;
import com.movie.wiki.business.repository.MovieRepository;
import com.movie.wiki.business.repository.ReviewRepository;
import com.movie.wiki.business.repository.model.Movie;
import com.movie.wiki.business.repository.model.Review;
import com.movie.wiki.exception.IdNotFound;
import com.movie.wiki.model.ReviewDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class ReviewServiceImplTest {

    @Mock
    private ReviewRepository repository;
    @Mock
    private ReviewMapper mapper;
    @Mock
    private MovieRepository movieRepository;
    @InjectMocks
    private ReviewServiceImpl service;

    ReviewDto dto;

    @BeforeEach
    void setUp() {
        dto = new ReviewDto();
        dto.setId(1L);
    }

    @Test
    void addReview_Success() {
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(new Movie()));
        when(mapper.dtoToEntity(any(ReviewDto.class))).thenReturn(new Review());
        when(repository.save(any(Review.class))).thenReturn(new Review());
        when(mapper.entityToDto(any(Review.class))).thenReturn(dto);
        assertEquals(dto, service.addReview(1L, dto));
    }

    @Test
    void addReview_BadMovieIdGiven() {
        when(movieRepository.findById(anyLong())).thenReturn(Optional.empty());
        Throwable error = catchThrowable(() -> service.addReview(1L, new ReviewDto()));
        assertThat(error)
                .isInstanceOf(IdNotFound.class)
                .hasMessage("No movie with id: 1");
    }

    @Test
    void updateReview_Success() {
        when(repository.existsById(anyLong())).thenReturn(true);
        when(mapper.dtoToEntity(any(ReviewDto.class))).thenReturn(new Review());
        when(repository.save(any(Review.class))).thenReturn(new Review());
        when(mapper.entityToDto(any(Review.class))).thenReturn(dto);
        verifyNoMoreInteractions(repository, mapper);
        assertEquals(dto, service.updateReview(dto));
    }

    @Test
    void updateReview_BadIdGiven() {
        when(repository.existsById(anyLong())).thenReturn(false);
        Throwable error = catchThrowable(() -> service.updateReview(dto));
        assertThat(error)
                .isInstanceOf(IdNotFound.class)
                .hasMessage("No review with id: 1");
    }

    @Test
    void deleteReview_Successful() {
        when(repository.existsById(anyLong())).thenReturn(true);
        service.deleteReview(0L);
        verify(repository).existsById(anyLong());
        verify(repository).deleteById(anyLong());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteReview_BadIdGiven() {
        when(repository.existsById(anyLong())).thenReturn(false);
        Throwable error = catchThrowable(() -> service.deleteReview(0L));
        assertThat(error)
                .isInstanceOf(IdNotFound.class)
                .hasMessage("No review with id: 0");
    }

    @Test
    void getReviewsFromMovie() {
        Review review = new Review();
        when(repository.findByMovieId_id(anyLong())).thenReturn(List.of(review));
        ReviewDto dto = new ReviewDto();
        when(mapper.entityToDto(any(Review.class))).thenReturn(dto);
        assertEquals(List.of(dto), service.getReviewsFromMovie(1L));
    }
}