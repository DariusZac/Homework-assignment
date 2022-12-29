package com.movie.wiki.business.mapper;

import com.movie.wiki.business.repository.model.MovieDetail;
import com.movie.wiki.model.MovieDetailDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieDetailMapper {
    MovieDetailDto entityToDto(MovieDetail detail);
    MovieDetail dtoToEntity(MovieDetailDto dto);
}
