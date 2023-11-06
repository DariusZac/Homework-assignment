package com.movie.wiki.controller;

import com.movie.wiki.business.service.MovieDetailService;
import com.movie.wiki.model.MovieDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/details")
public class MovieDetailController {
    private final MovieDetailService service;

    @GetMapping("/{id}")
    public ResponseEntity<MovieDetailDto> getDetails(@PathVariable Long id) {
        return ResponseEntity.ok(service.getDetails(id));
    }

    @PostMapping
    public ResponseEntity<MovieDetailDto> addDetails(@Validated @RequestBody MovieDetailDto dto) {
        return ResponseEntity.ok(service.addDetails(dto));
    }

}
