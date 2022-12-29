package com.movie.wiki.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class MovieDetailDto {
    private Long id;
    @NotBlank
    private String description;
    private int budget;
    @NotNull
    private LocalDate date;
}
