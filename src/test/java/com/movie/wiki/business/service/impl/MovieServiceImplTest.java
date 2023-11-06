package com.movie.wiki.business.service.impl;

import com.movie.wiki.business.mapper.MovieMapper;
import com.movie.wiki.business.repository.ActorRepository;
import com.movie.wiki.business.repository.MovieRepository;
import com.movie.wiki.business.repository.ReviewRepository;
import com.movie.wiki.business.repository.model.Actor;
import com.movie.wiki.business.repository.model.Movie;
import com.movie.wiki.business.repository.model.Review;
import com.movie.wiki.exception.IdNotFound;
import com.movie.wiki.model.ActorNMovie;
import com.movie.wiki.model.MovieDto;
import com.movie.wiki.model.TopMovies;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class MovieServiceImplTest {

    @Mock
    private MovieRepository repository;
    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private ActorRepository actorRepository;
    @Mock
    private MovieMapper mapper;
    @Mock
    private Movie movie;
    @InjectMocks
    private MovieServiceImpl service;

    @Test
    void getAllMovies() {
        //given
        when(repository.findAll()).thenReturn(List.of(new Movie()));
        MovieDto dto = new MovieDto();
        when(mapper.movieToDto(any(Movie.class))).thenReturn(dto);
        //when-then
        assertEquals(List.of(dto), service.getAllMovies());
    }

    @Test
    void addMovie() {
        //given
        MovieDto dto = new MovieDto();
        dto.setId(0L);
        when(mapper.dtoToMovie(any(MovieDto.class))).thenReturn(new Movie());
        when(repository.save(any(Movie.class))).thenReturn(new Movie());
        when(mapper.movieToDto(any(Movie.class))).thenReturn(dto);
        //when-then
        assertEquals(dto, service.addMovie(dto));
    }

    @Test
    void updateMovie_Success() {
        //given
        MovieDto dto = new MovieDto();
        dto.setId(1L);
        when(repository.existsById(anyLong())).thenReturn(true);
        when(mapper.dtoToMovie(any(MovieDto.class))).thenReturn(new Movie());
        when(repository.save(any(Movie.class))).thenReturn(new Movie());
        when(mapper.movieToDto(any(Movie.class))).thenReturn(dto);
        //when-then
        verifyNoMoreInteractions(repository, mapper);
        assertEquals(dto, service.updateMovie(dto));
    }

    @Test
    void updateMovie_BadIdGiven() {
        //given
        MovieDto dto = new MovieDto();
        dto.setId(1L);
        when(repository.existsById(anyLong())).thenReturn(false);
        Throwable error = catchThrowable(() -> service.updateMovie(dto));
        //when-then
        assertThat(error)
                .isInstanceOf(IdNotFound.class)
                .hasMessage("No movie with id: 1");
    }

    @Test
    void deleteMovie_Success() {
        //given
        when(repository.existsById(anyLong())).thenReturn(true);
        //when
        service.deleteMovie(0L);
        //then
        verify(repository).existsById(anyLong());
        verify(repository).deleteById(anyLong());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteMovie_BadIdGiven() {
        //given
        when(repository.existsById(anyLong())).thenReturn(false);
        Throwable error = catchThrowable(() -> service.deleteMovie(0L));
        //when-then
        assertThat(error)
                .isInstanceOf(IdNotFound.class)
                .hasMessage("No movie with id: 0");
    }

//    @Test
//    void addActor_BadMovieIdGiven() {
//        //given
//        when(repository.existsById(anyLong())).thenReturn(false);
//        //when-then
//        assertFalse(service.addActor(0L, 0L));
//    }

    @Test
    void addActor_BadActorIdGiven() {
        //when
        ResponseEntity<Object> objectResponseEntity = service.addActor(0L, 0L);
        //then
        assertEquals(HttpStatus.NOT_FOUND, objectResponseEntity.getStatusCode());
    }

    @Test
    void addActor_Success() {
        //given
        Actor actor = new Actor();
        when(repository.findById(anyLong())).thenReturn(Optional.of(movie));
        when(actorRepository.findById(anyLong())).thenReturn(Optional.of(actor));
        //when
        ResponseEntity<Object> objectResponseEntity = service.addActor(0L, 0L);
        //then
        assertEquals(HttpStatus.OK, objectResponseEntity.getStatusCode());
    }

    @Test
    void getMovieActors_Success() {
        //given
        ActorNMovie dto = new ActorNMovie();
        when(repository.findById(anyLong())).thenReturn(Optional.of(movie));
        when(mapper.entityToActorNMovieDto(any(Movie.class))).thenReturn(dto);
        //when-then
        assertEquals(dto, service.getMovieActors(0L));
    }

    @Test
    void getMovieActors_BadIdGiven() {
        //given
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        Throwable error = catchThrowable(() -> service.getMovieActors(0L));
        //when-then
        assertThat(error)
                .isInstanceOf(IdNotFound.class)
                .hasMessage("No movie with id: 0");
    }

    @Test
    void getTopRatedMovies_Success() {
        //given
        Review review = new Review();
        review.setScore(7);
        Review review2 = new Review();
        review2.setScore(5);
        Review secondMovieReview = new Review();
        secondMovieReview.setScore(7);
        Review secondMovieReview2 = new Review();
        secondMovieReview2.setScore(9);
        Movie movie1 = new Movie(1L, "Terminator", null, null, null);
        Movie movie2 = new Movie(2L, "Avatar", null, null, null);
        when(repository.findAll()).thenReturn(List.of(movie1, movie2));
        when(reviewRepository.findAllByMovieId_id(1L)).thenReturn(List.of(review, review2));
        when(reviewRepository.findAllByMovieId_id(2L)).thenReturn(List.of(secondMovieReview, secondMovieReview2));
        TopMovies topMovies = new TopMovies(1L, "Terminator", 6, 2);
        TopMovies topMovies1 = new TopMovies(2L, "Avatar", 8, 2);
        List<TopMovies> topMovie = List.of(topMovies1, topMovies);
        //when-then
        assertEquals(topMovie, service.getTopRatedMovies());
    }
}