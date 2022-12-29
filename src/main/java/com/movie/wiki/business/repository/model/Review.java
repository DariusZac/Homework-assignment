package com.movie.wiki.business.repository.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String reviewer;
    @Column
    private String review;
    @Column
    private int score;
    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movieId;
}
