package com.huugoncalves.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huugoncalves.dsmovie.entities.Score;
import com.huugoncalves.dsmovie.entities.ScorePK;

public interface ScoreRepository extends JpaRepository<Score, ScorePK> {

}
