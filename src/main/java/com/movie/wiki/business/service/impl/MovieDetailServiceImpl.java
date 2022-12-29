package com.movie.wiki.business.service.impl;

import com.movie.wiki.business.mapper.MovieDetailMapper;
import com.movie.wiki.business.repository.MovieDetailRepository;
import com.movie.wiki.business.repository.MovieRepository;
import com.movie.wiki.business.repository.model.Movie;
import com.movie.wiki.business.repository.model.MovieDetail;
import com.movie.wiki.business.service.MovieDetailService;
import com.movie.wiki.exception.IdNotFound;
import com.movie.wiki.model.MovieDetailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieDetailServiceImpl implements MovieDetailService {
    private final MovieDetailRepository repository;
    private final MovieRepository movieRepository;
    private final MovieDetailMapper mapper;

    @Override
    public MovieDetailDto getDetails(Long id) {
        MovieDetail foundDetails = repository.findById(id).orElseThrow(
                        ()-> new IdNotFound("No details with id: "+id));
        return mapper.entityToDto(foundDetails);
    }

    @Override
    public MovieDetailDto addDetails(MovieDetailDto dto) {
        Movie entityMovie = movieRepository.findById(dto.getId()).orElseThrow(
                ()-> new IdNotFound("No details with id: "+dto.getId()));
        MovieDetail entityDetails = mapper.dtoToEntity(dto);
        entityDetails.setMovieId(entityMovie);
        log.info("saving details {}", entityDetails);
        repository.save(entityDetails);
        return mapper.entityToDto(entityDetails);
    }
}
