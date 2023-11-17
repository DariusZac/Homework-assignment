package com.movie.wiki.business.repository.model;

import com.movie.wiki.business.repository.MovieScore;
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
import java.util.Date;

@Entity
@Data
@Table(name = "review")
public class Review implements MovieScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String reviewer;
    @Column(name = "review")
    private String movieReview;
    @Column
    private int score;
    @Column(name = "date_created")
    @CreationTimestamp
    private Date date;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movieId;
}
