package com.mink.demo.bowlingapi.service;

import com.mink.demo.bowlingapi.model.entities.Game;
import com.mink.demo.bowlingapi.model.repositories.GameRepository;
import com.mink.demo.bowlingapi.service.dto.GameRequest;
import com.mink.demo.bowlingapi.service.dto.GameResponse;
import com.mink.demo.bowlingapi.service.mapper.GameMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class GameService {

    private final GameRepository gameRepository;

    private final GameMapper gameMapper;


    @Autowired
    public GameService(GameRepository gameRepository, GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
    }

    public Page<GameResponse> getGames(Pageable pageable) {
        return gameRepository.findAll(pageable).map(gameMapper::gameToGameResponse);
    }

    public GameResponse getGameById(long gameId) {
        return gameMapper.gameToGameResponse(gameRepository.getOne(gameId));
    }

    public GameResponse saveGame(GameRequest gameRequest) {
        Game game = gameMapper.gameRequestToGame(gameRequest);
        return gameMapper.gameToGameResponse(gameRepository.save(game));
    }

    public void deleteGameById(Long gameId) {
        Game existingGame = gameRepository.getOne(gameId);
        gameRepository.deleteById(existingGame.getId());
    }

}
