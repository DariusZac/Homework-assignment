package com.movie.wiki.business.service.impl;

import com.movie.wiki.business.mapper.ReviewMapper;
import com.movie.wiki.business.repository.MovieRepository;
import com.movie.wiki.business.repository.ReviewRepository;
import com.movie.wiki.business.repository.model.Movie;
import com.movie.wiki.business.repository.model.Review;
import com.movie.wiki.business.service.ReviewService;
import com.movie.wiki.exception.IdNotFound;
import com.movie.wiki.model.ReviewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository repository;
    private final MovieRepository movieRepository;
    private final ReviewMapper mapper;

    @Override
    public ReviewDto addReview(Long movieId, ReviewDto dto) {
        Movie addToMovie = movieRepository.findById(movieId).orElseThrow(() -> new IdNotFound("No review with id: "+ movieId));
        dto.setId(0L);
        Review review = mapper.dtoToEntity(dto);
        review.setMovieId(addToMovie);
        log.info("saving {} review", dto);
        return mapper.entityToDto(repository.save(review));
    }

    @Override
    public ReviewDto updateReview(ReviewDto dto) {
        if (!repository.existsById(dto.getId())){
            throw new IdNotFound("No review with id: "+ dto.getId());
        }
        return mapper.entityToDto(repository.save(mapper.dtoToEntity(dto)));
    }

    @Override
    public void deleteReview(Long id) {
        if (!repository.existsById(id)){
            throw new IdNotFound("No review with id: "+id);
        }
        repository.deleteById(id);
        log.info("Review with id {} is deleted", id);
    }

    @Override
    public List<ReviewDto> getReviewsFromMovie(Long id) {
        List<Review> reviews = repository.findByMovieId_id(id);
        return reviews.stream().map(mapper::entityToDto).collect(Collectors.toList());
    }
}
