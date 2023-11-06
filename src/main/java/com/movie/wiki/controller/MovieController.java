package com.movie.wiki.controller;

import com.movie.wiki.business.service.MovieService;
import com.movie.wiki.model.ActorNMovie;
import com.movie.wiki.model.MovieDto;
import com.movie.wiki.model.TopMovies;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieService service;

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return ResponseEntity.ok(service.getAllMovies());
    }

    @PostMapping
    public ResponseEntity<MovieDto> addMovie(@Validated @RequestBody MovieDto dto) {
        return ResponseEntity.ok(service.addMovie(dto));
    }

    @PatchMapping
    public ResponseEntity<MovieDto> updateMovie(@Validated @RequestBody MovieDto dto) {
        return ResponseEntity.ok(service.updateMovie(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMovie(@PathVariable("id") long id) {
        service.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/actor")
    public ResponseEntity<Object> addActor(@RequestParam Long movieId, @RequestParam Long actorId) {
        return service.addActor(movieId, actorId);
    }

    @GetMapping("/{movieId}/actors")
    public ResponseEntity<ActorNMovie> getMovieActors(@PathVariable Long movieId) {
        return ResponseEntity.ok(service.getMovieActors(movieId));
    }

    @GetMapping("/top")
    public ResponseEntity<List<TopMovies>> getTopRatedMovies() {
        return ResponseEntity.ok(service.getTopRatedMovies());
    }

}
