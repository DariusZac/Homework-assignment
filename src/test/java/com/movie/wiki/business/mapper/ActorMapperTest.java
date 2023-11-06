package com.movie.wiki.business.mapper;

import com.movie.wiki.business.repository.model.Actor;
import com.movie.wiki.model.ActorDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith({MockitoExtension.class})
class ActorMapperTest {

    @InjectMocks
    private ActorMapperImpl mapper;

    @Test
    void dtoToEntity() {
        //given
        ActorDto actorDto = new ActorDto();
        actorDto.setFullName("Test");
        actorDto.setId(10L);
        //when
        Actor mappedActor = mapper.dtoToEntity(actorDto);
        //then
        assertNull(mappedActor.getDate());
        assertNull(mappedActor.getNationality());
        assertEquals(actorDto.getId(), mappedActor.getId());
        assertEquals(actorDto.getFullName(), mappedActor.getFullName());
    }

    @Test
    void entityToDto() {
        //given
        Actor actor = new Actor();
        actor.setId(10L);
        actor.setFullName("Test");
        //when
        ActorDto mappedActor = mapper.entityToDto(actor);
        //then
        assertNull(mappedActor.getDate());
        assertNull(mappedActor.getNationality());
        assertEquals(actor.getId(), mappedActor.getId());
        assertEquals(actor.getFullName(), mappedActor.getFullName());
    }
}