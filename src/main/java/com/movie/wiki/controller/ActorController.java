package com.movie.wiki.controller;

import com.movie.wiki.business.service.ActorService;
import com.movie.wiki.model.ActorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/actors")
public class ActorController {
    private final ActorService service;

    @GetMapping
    public ResponseEntity<List<ActorDto>> getAllActors() {
        return ResponseEntity.ok().body(service.getAllActors());
    }

    @PostMapping
    public ResponseEntity<ActorDto> addActor(@Validated @RequestBody ActorDto dto) {
        return ResponseEntity.ok().body(service.addActor(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteActor(@PathVariable Long id) {
        service.deleteActor(id);
        return ResponseEntity.noContent().build();
    }
}
