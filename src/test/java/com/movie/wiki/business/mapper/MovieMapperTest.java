package com.movie.wiki.business.mapper;

import com.movie.wiki.business.repository.model.Movie;
import com.movie.wiki.model.ActorNMovie;
import com.movie.wiki.model.MovieDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith({MockitoExtension.class})
class MovieMapperTest {

    @InjectMocks
    private MovieMapperImpl mapper;

    @Test
    void movieToDto() {
        //given
        Movie movie = new Movie();
        movie.setId(10L);
        //when
        MovieDto mapped = mapper.movieToDto(movie);
        //then
        assertNull(mapped.getName());
        assertEquals(movie.getId(), mapped.getId());
    }

    @Test
    void dtoToMovie() {
        //given
        MovieDto movieDto = new MovieDto();
        movieDto.setId(10L);
        //when
        Movie mapped = mapper.dtoToMovie(movieDto);
        //then
        assertNull(mapped.getName());
        assertEquals(movieDto.getId(), mapped.getId());
    }

    @Test
    void entityToActorNMovieDto() {
        //given
        Movie movie = new Movie();
        movie.setId(10L);
        //when
        ActorNMovie mapped = mapper.entityToActorNMovieDto(movie);
        //then
        assertNull(mapped.getName());
        assertNull(mapped.getActors());
        assertEquals(movie.getId(), mapped.getId());
    }
}