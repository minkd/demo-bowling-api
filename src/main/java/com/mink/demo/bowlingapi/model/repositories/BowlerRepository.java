package com.mink.demo.bowlingapi.model.repositories;

import com.mink.demo.bowlingapi.model.entities.Bowler;

import java.util.Set;

public interface BowlerRepository extends BaseLookupRepository<Bowler, Long> {

    Set<Bowler> findBowlersByGameId(Long gameId);

    Bowler findOneByGameIdAndId(Long gameId, Long id);

    void deleteByGameIdAndId(Long gameId, Long id);

}
