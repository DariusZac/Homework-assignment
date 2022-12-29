package com.movie.wiki.util;

import com.movie.wiki.model.TopMovies;

import java.util.Comparator;

public class TopMovieComparator implements Comparator<TopMovies> {
    @Override
    public int compare(TopMovies o1, TopMovies o2) {
        return Integer.valueOf ((int) o2.getAverageScore()).compareTo((int) o1.getAverageScore());
    }
}
