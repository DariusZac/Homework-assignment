package com.movie.wiki.business.service;

import com.movie.wiki.model.ActorDto;

import java.util.List;

public interface ActorService {
    List<ActorDto> getAllActors();
    void deleteActor(Long id);
    ActorDto addActor(ActorDto dto);
}
