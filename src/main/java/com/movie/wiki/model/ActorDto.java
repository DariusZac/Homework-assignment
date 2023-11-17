package com.movie.wiki.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class ActorDto {
    private Long id;
    @NotBlank
    private String fullName;
    private String nationality;
    private LocalDate dateOfBirth;
}
