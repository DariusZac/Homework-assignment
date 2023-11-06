package com.movie.wiki.controller;

import com.movie.wiki.business.service.ReviewService;
import com.movie.wiki.model.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService service;

    @GetMapping("/{id}")
    public ResponseEntity<List<ReviewDto>> getAllReviewsFromMovie(@PathVariable Long id) {
        return ResponseEntity.ok(service.getReviewsFromMovie(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReview(@PathVariable("id") long id) {
        service.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{movieId}")
    public ResponseEntity<ReviewDto> addReview(@PathVariable Long movieId, @Validated @RequestBody ReviewDto dto) {
        return ResponseEntity.ok(service.addReview(movieId, dto));
    }

    @PatchMapping
    public ResponseEntity<ReviewDto> updateReview(@Validated @RequestBody ReviewDto dto) {
        return ResponseEntity.ok(service.updateReview(dto));
    }
}
