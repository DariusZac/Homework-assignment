package com.movie.wiki.business.service.impl;

import com.movie.wiki.business.mapper.ActorMapper;
import com.movie.wiki.business.repository.ActorRepository;
import com.movie.wiki.business.repository.model.Actor;
import com.movie.wiki.exception.IdNotFound;
import com.movie.wiki.model.ActorDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class ActorServiceImplTest {

    @Mock
    private ActorRepository repository;
    @Mock
    private ActorMapper mapper;
    @InjectMocks
    private ActorServiceImpl service;

    @Test
    void getAllActors() {
        Actor actor = new Actor();
        when(repository.findAll()).thenReturn(List.of(actor));
        ActorDto actorDto = new ActorDto();
        when(mapper.entityToDto(any())).thenReturn(actorDto);

        List<ActorDto> actors = service.getAllActors();
        assertEquals(actors.get(0), actorDto);
    }

    @Test
    void deleteActor_Positive() {
        when(repository.existsById(anyLong())).thenReturn(true);
        service.deleteActor(0L);
        verify(repository).existsById(anyLong());
        verify(repository).deleteById(anyLong());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteActor_BadIdGiven() {
        when(repository.existsById(anyLong())).thenReturn(false);
        Throwable error = catchThrowable(() -> service.deleteActor(0L));
        assertThat(error)
                .isInstanceOf(IdNotFound.class)
                .hasMessage("No actor with id: 0");
    }

    @Test
    void addActor() {
        Actor actor = new Actor();
        when(mapper.dtoToEntity(any())).thenReturn(actor);
        when(repository.save(any())).thenReturn(actor);
        ActorDto dto = new ActorDto();
        when(mapper.entityToDto(any(Actor.class))).thenReturn(dto);

        assertEquals(dto, service.addActor(dto));
    }
}