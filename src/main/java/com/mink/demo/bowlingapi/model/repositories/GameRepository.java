package com.mink.demo.bowlingapi.model.repositories;

import com.mink.demo.bowlingapi.model.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
