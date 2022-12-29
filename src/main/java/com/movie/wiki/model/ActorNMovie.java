package com.movie.wiki.model;

import com.movie.wiki.business.repository.model.Actor;
import lombok.Data;

import java.util.List;

@Data
public class ActorNMovie {
    private Long id;
    private String name;
    private List<Actor> actors;
}
