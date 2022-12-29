package com.movie.wiki.business.service.impl;

import com.movie.wiki.business.mapper.ActorMapper;
import com.movie.wiki.business.repository.ActorRepository;
import com.movie.wiki.business.repository.model.Actor;
import com.movie.wiki.business.service.ActorService;
import com.movie.wiki.exception.IdNotFound;
import com.movie.wiki.model.ActorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {
    private final ActorRepository repository;
    private final ActorMapper mapper;

    @Override
    public List<ActorDto> getAllActors() {
        return repository.findAll().stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteActor(Long id) {
        if (!repository.existsById(id)){
            throw new IdNotFound("No actor with id: "+id);
        }
        repository.deleteById(id);
        log.info("Actor with id {} is deleted", id);
    }

    @Override
    public ActorDto addActor(ActorDto dto) {
        Actor addActor = mapper.dtoToEntity(dto);
        log.info("saving {}", addActor);
        return mapper.entityToDto(repository.save(addActor));
    }
}
