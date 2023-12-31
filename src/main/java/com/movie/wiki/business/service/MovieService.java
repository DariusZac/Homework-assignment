package com.movie.wiki.business.service;

import com.movie.wiki.model.ActorNMovie;
import com.movie.wiki.model.MovieDto;
import com.movie.wiki.model.TopMovies;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {
    List<MovieDto> getAllMovies();
    MovieDto addMovie(MovieDto dto);
    MovieDto updateMovie(MovieDto dto);
    void deleteMovie(Long id);
    ResponseEntity<Object> addActor(Long movieId, Long actorId);
    ActorNMovie getMovieActors(Long movieId);
    List<TopMovies> getTopRatedMovies();
}
