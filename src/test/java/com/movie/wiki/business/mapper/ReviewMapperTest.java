package com.movie.wiki.business.mapper;

import com.movie.wiki.business.repository.model.Review;
import com.movie.wiki.model.ReviewDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith({MockitoExtension.class})
class ReviewMapperTest {

    @InjectMocks
    private ReviewMapperImpl mapper;

    @Test
    void dtoToEntity() {
        //given
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(10L);
        reviewDto.setReview("Test");
        reviewDto.setScore(5);
        //when
        Review mapped = mapper.dtoToEntity(reviewDto);
        //then
        assertNull(mapped.getReviewer());
        assertNotNull(mapped.getDate());
        assertEquals(reviewDto.getId(), mapped.getId());
        assertEquals(reviewDto.getReview(), mapped.getMovieReview());
        assertEquals(reviewDto.getScore(), mapped.getScore());
    }

    @Test
    void entityToDto() {
        //given
        Review review = new Review();
        review.setId(10L);
        review.setMovieReview("Test");
        review.setScore(5);
        //when
        ReviewDto mapped = mapper.entityToDto(review);
        //then
        assertNull(mapped.getReviewer());
        assertNull(mapped.getDate());
        assertEquals(review.getId(), mapped.getId());
        assertEquals(review.getMovieReview(), mapped.getReview());
        assertEquals(review.getScore(), mapped.getScore());
    }
}