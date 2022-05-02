package com.example.dwspractica;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating>findBygame(Game game);
    Rating findById(long id);
}
