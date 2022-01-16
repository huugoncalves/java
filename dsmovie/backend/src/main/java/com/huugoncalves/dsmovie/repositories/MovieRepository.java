package com.huugoncalves.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huugoncalves.dsmovie.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
