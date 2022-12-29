package com.movie.wiki.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopMovies {
    private Long movieId;
    private String movieTitle;
    private float averageScore;
    private int numberOfReviews;
}
