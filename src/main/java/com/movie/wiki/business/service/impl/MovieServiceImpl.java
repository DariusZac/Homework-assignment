package com.movie.wiki.business.service.impl;

import com.movie.wiki.business.mapper.MovieMapper;
import com.movie.wiki.business.repository.ActorRepository;
import com.movie.wiki.business.repository.MovieRepository;
import com.movie.wiki.business.repository.MovieScore;
import com.movie.wiki.business.repository.ReviewRepository;
import com.movie.wiki.business.repository.model.Movie;
import com.movie.wiki.business.service.MovieService;
import com.movie.wiki.exception.IdNotFound;
import com.movie.wiki.model.ActorNMovie;
import com.movie.wiki.model.MovieDto;
import com.movie.wiki.model.TopMovies;
import com.movie.wiki.util.TopMovieComparator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
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
            throw new IdNotFound("No movie with id: " + dto.getId());
        }
        log.info("Updating entity with id: {}", dto.getId());
        return mapper.movieToDto(repository.save(mapper.dtoToMovie(dto)));
    }

    @Override
    public void deleteMovie(Long id) {
        if (!repository.existsById(id)) {
            throw new IdNotFound("No movie with id: " + id);
        }
        repository.deleteById(id);
        log.info("Movie with id {} is deleted", id);
    }

    @Override
    public boolean addActor(Long movieId, Long actorId) {
        if (!(repository.existsById(movieId) && actorRepository.existsById(actorId)))
            return false;
        Movie movie = repository.findById(movieId).get();
        movie.addActor(actorRepository.findById(actorId).get());
        repository.save(movie);
        return true;
    }

    @Override
    public ActorNMovie getMovieActors(Long movieId) {
        Movie movie = repository.findById(movieId).orElseThrow(() -> new IdNotFound("No movie with id: " + movieId));
        return mapper.EntityToActorNMovieDto(movie);
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
            if (review.size() != 0) {
                averageScore = averageScore / review.size();
            }
            topMovies.add(new TopMovies(movie.getId(), movie.getName(), averageScore, review.size()));
        }
        Collections.sort(topMovies, new TopMovieComparator());
        return topMovies;
    }
}
