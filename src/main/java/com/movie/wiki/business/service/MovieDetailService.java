package com.movie.wiki.business.service;

import com.movie.wiki.model.MovieDetailDto;

public interface MovieDetailService {
    MovieDetailDto getDetails(Long id);
    MovieDetailDto addDetails(MovieDetailDto dto);
}
