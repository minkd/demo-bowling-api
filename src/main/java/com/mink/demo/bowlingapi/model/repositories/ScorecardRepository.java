package com.mink.demo.bowlingapi.model.repositories;

import com.mink.demo.bowlingapi.model.entities.Scorecard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScorecardRepository extends JpaRepository<Scorecard, Long> {

    List<Scorecard> findScorecardsByGameId(Long gameId);

    Scorecard findOneByGameIdAndId(Long gameId, Long id);

    Scorecard findOneByGameIdAndBowlerId(Long gameId, Long bowlerId);

    void deleteByGameIdAndId(Long gameId, Long id);

}
