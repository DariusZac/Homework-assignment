package com.movie.wiki.business.mapper;

import com.movie.wiki.business.repository.model.Actor;
import com.movie.wiki.model.ActorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    Actor dtoToEntity(ActorDto dto);
    ActorDto entityToDto(Actor entity);
}
