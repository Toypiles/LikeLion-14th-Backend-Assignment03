package com.likelion.likelionassignmentcrud.movie.domain.repository;

import com.likelion.likelionassignmentcrud.director.domain.Director;
import com.likelion.likelionassignmentcrud.movie.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByDirector(Director director);
}
