package com.movie.wiki.business.repository;

import com.movie.wiki.business.repository.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
//    @Query("select s from Review s where movieId.id = ?1")
    List<Review> findByMovieId_id(long id);
    List<MovieScore> findAllByMovieId_id(Long id);
}
