package com.movie.wiki.business.mapper;

import com.movie.wiki.business.repository.model.MovieDetail;
import com.movie.wiki.model.MovieDetailDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MovieDetailMapper {
    MovieDetailDto entityToDto(MovieDetail detail);

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    MovieDetail dtoToEntity(MovieDetailDto dto);
}
