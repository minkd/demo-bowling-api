package com.mink.demo.bowlingapi.service;

import com.mink.demo.bowlingapi.model.entities.Bowler;
import com.mink.demo.bowlingapi.model.entities.Game;
import com.mink.demo.bowlingapi.model.repositories.BowlerRepository;
import com.mink.demo.bowlingapi.model.repositories.GameRepository;
import com.mink.demo.bowlingapi.service.dto.BowlerRequest;
import com.mink.demo.bowlingapi.service.dto.BowlerResponse;
import com.mink.demo.bowlingapi.service.mapper.BowlerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@Transactional
public class BowlerService {

    private final GameRepository gameRepository;

    private final BowlerRepository bowlerRepository;

    private final BowlerMapper bowlerMapper;

    @Autowired
    public BowlerService(GameRepository gameRepository, BowlerRepository bowlerRepository, BowlerMapper bowlerMapper) {
        this.gameRepository = gameRepository;
        this.bowlerRepository = bowlerRepository;
        this.bowlerMapper = bowlerMapper;
    }

    public Set<BowlerResponse> getBowlersByGameId(Long gameId) {
       return bowlerMapper.bowlersToBowlerResponses(bowlerRepository.findBowlersByGameId(gameId));
    }

    public BowlerResponse getBowlerByGameIdById(Long gameId, Long bowlerId) {
        return bowlerMapper.bowlerToBowlerResponse(bowlerRepository.findOneByGameIdAndId(gameId, bowlerId));
    }

    public Set<BowlerResponse> addBowlerToGame(Long gameId, BowlerRequest bowlerRequest) {
        Game game = gameRepository.getOne(gameId);
        Bowler bowler = bowlerMapper.bowlerRequestToBowler(bowlerRequest);
        bowler.setGame(game);
        bowlerRepository.save(bowler);
        return getBowlersByGameId(gameId);
    }

    public void deleteBowlerByGameIdById(Long gameId, Long bowlerId) {
        bowlerRepository.deleteByGameIdAndId(gameId, bowlerId);
    }

}
