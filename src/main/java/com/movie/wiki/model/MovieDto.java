package com.movie.wiki.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Data
public class MovieDto {
    private Long id;
    @NotBlank
    private String name;
}
