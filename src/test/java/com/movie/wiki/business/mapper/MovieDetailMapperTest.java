package com.movie.wiki.business.mapper;

import com.movie.wiki.business.repository.model.MovieDetail;
import com.movie.wiki.model.MovieDetailDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith({MockitoExtension.class})
class MovieDetailMapperTest {

    @InjectMocks
    private MovieDetailMapperImpl mapper;

    @Test
    void entityToDto() {
        //given
        MovieDetail movieDetail = new MovieDetail();
        movieDetail.setId(10L);
        movieDetail.setBudget(100);
        //when
        MovieDetailDto mapped = mapper.entityToDto(movieDetail);
        //then
        assertNull(mapped.getDate());
        assertNull(mapped.getDescription());
        assertEquals(movieDetail.getId(), mapped.getId());
        assertEquals(movieDetail.getBudget(), mapped.getBudget());
    }

    @Test
    void dtoToEntity() {
        //given
        MovieDetailDto movieDetail = new MovieDetailDto();
        movieDetail.setId(10L);
        movieDetail.setBudget(100);
        //when
        MovieDetail mapped = mapper.dtoToEntity(movieDetail);
        //then
        assertNull(mapped.getDate());
        assertNull(mapped.getDescription());
        assertEquals(movieDetail.getId(), mapped.getId());
        assertEquals(movieDetail.getBudget(), mapped.getBudget());
    }
}