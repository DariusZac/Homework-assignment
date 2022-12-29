package com.movie.wiki.business.repository;

import com.movie.wiki.business.repository.model.MovieDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDetailRepository extends JpaRepository<MovieDetail, Long> {
}
