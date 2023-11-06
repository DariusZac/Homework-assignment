package com.movie.wiki.business.mapper;

import com.movie.wiki.business.repository.model.Movie;
import com.movie.wiki.model.ActorNMovie;
import com.movie.wiki.model.MovieDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieMapper {
    MovieDto movieToDto(Movie movie);

    Movie dtoToMovie(MovieDto dto);

    ActorNMovie entityToActorNMovieDto(Movie movie);


}
