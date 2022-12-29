package com.movie.wiki.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ReviewDto {
    private Long id;
    @NotBlank
    private String reviewer;
    private String review;
    @NotNull
    private int score;
    @Nullable
    private LocalDate date;
}
