package com.movie.wiki.business.service.impl;

import com.movie.wiki.business.mapper.MovieMapper;
import com.movie.wiki.business.repository.ActorRepository;
import com.movie.wiki.business.repository.MovieRepository;
import com.movie.wiki.business.repository.MovieScore;
import com.movie.wiki.business.repository.ReviewRepository;
import com.movie.wiki.business.repository.model.Actor;
import com.movie.wiki.business.repository.model.Movie;
import com.movie.wiki.business.service.MovieService;
import com.movie.wiki.exception.IdNotFound;
import com.movie.wiki.model.ActorNMovie;
import com.movie.wiki.model.MovieDto;
import com.movie.wiki.model.TopMovies;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private static final String NO_MOVIE = "No movie with id: ";
    private final MovieRepository repository;
    private final ActorRepository actorRepository;
    private final ReviewRepository reviewRepository;
    private final MovieMapper mapper;

    @Override
    public List<MovieDto> getAllMovies() {
        List<Movie> foundMovies = repository.findAll();
        log.info("found movie size: {}", foundMovies.size());
        return foundMovies.stream().map(mapper::movieToDto).collect(Collectors.toList());
    }

    @Override
    public MovieDto addMovie(MovieDto dto) {
        dto.setId(0L);
        log.info("saving dto :{}", dto);
        return mapper.movieToDto(repository.save(mapper.dtoToMovie(dto)));
    }

    @Override
    public MovieDto updateMovie(MovieDto dto) {
        if (!repository.existsById(dto.getId())) {
            throw new IdNotFound(NO_MOVIE + dto.getId());
        }
        log.info("Updating entity with id: {}", dto.getId());
        return mapper.movieToDto(repository.save(mapper.dtoToMovie(dto)));
    }

    @Override
    public void deleteMovie(Long id) {
        if (!repository.existsById(id)) {
            throw new IdNotFound(NO_MOVIE + id);
        }
        repository.deleteById(id);
        log.info("Movie with id {} is deleted", id);
    }

    @Override
    public ResponseEntity<Object> addActor(Long movieId, Long actorId) {
        Optional<Movie> movie = repository.findById(movieId);
        Optional<Actor> actor = actorRepository.findById(actorId);
        if (movie.isPresent() && actor.isPresent()) {
            Movie existingMovie = movie.get();
            existingMovie.addActor(actor.get());
            repository.save(existingMovie);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ActorNMovie getMovieActors(Long movieId) {
        Movie movie = repository.findById(movieId).orElseThrow(() -> new IdNotFound(NO_MOVIE + movieId));
        return mapper.entityToActorNMovieDto(movie);
    }

    @Override
    public List<TopMovies> getTopRatedMovies() {
        List<TopMovies> topMovies = new ArrayList<>();
        List<Movie> movies = repository.findAll();
        for (Movie movie : movies) {
            List<MovieScore> review = reviewRepository.findAllByMovieId_id(movie.getId());
            float averageScore = 0;
            for (MovieScore score : review) {
                averageScore += score.getScore();
            }
            if (!review.isEmpty()) {
                averageScore = averageScore / review.size();
            }
            topMovies.add(new TopMovies(movie.getId(), movie.getName(), averageScore, review.size()));
        }
        Comparator<TopMovies> topMoviesComparator = Comparator.comparing(TopMovies::getAverageScore);
        topMovies.sort(topMoviesComparator.reversed());
        return topMovies;
    }
}
