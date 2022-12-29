package com.movie.wiki.business.service;

import com.movie.wiki.model.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto addReview(Long movieId, ReviewDto dto);
    ReviewDto updateReview(ReviewDto dto);
    void deleteReview(Long id);
    List<ReviewDto> getReviewsFromMovie(Long id);
}
