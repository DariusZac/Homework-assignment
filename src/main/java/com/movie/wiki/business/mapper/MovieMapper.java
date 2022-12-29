package com.movie.wiki.business.mapper;

import com.movie.wiki.business.repository.model.Movie;
import com.movie.wiki.model.ActorNMovie;
import com.movie.wiki.model.MovieDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieDto movieToDto(Movie movie);
    Movie dtoToMovie(MovieDto dto);

    ActorNMovie EntityToActorNMovieDto(Movie movie);


}
