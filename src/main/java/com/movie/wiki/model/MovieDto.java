package com.movie.wiki.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MovieDto {
    private Long id;
    @NotBlank
    private String name;
}
