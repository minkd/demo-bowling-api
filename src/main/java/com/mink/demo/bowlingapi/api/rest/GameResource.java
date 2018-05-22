package com.mink.demo.bowlingapi.api.rest;


import com.mink.demo.bowlingapi.service.GameService;
import com.mink.demo.bowlingapi.service.dto.BowlerRequest;
import com.mink.demo.bowlingapi.service.dto.BowlerResponse;
import com.mink.demo.bowlingapi.service.dto.GameRequest;
import com.mink.demo.bowlingapi.service.dto.GameResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = {"/v1/game"}, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Games", description = "Operations to manage Games")
public class GameResource {

    private final GameService gameService;

    @Autowired
    public GameResource(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all games", response = Page.class)
    public Page<GameResponse> getGames(Pageable pageable) {
        return gameService.getGames(pageable);
    }

    @GetMapping("/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Lookup a game by id", response = GameResponse.class)
    public GameResponse getGameById(@PathVariable Long gameId) {
        return gameService.getGameById(gameId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new game", response = GameResponse.class)
    public GameResponse saveGame(@RequestBody @Valid GameRequest gameRequest) {
        return gameService.saveGame(gameRequest);
    }

    @DeleteMapping("/{gameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete a game")
    public void deleteGameById(@PathVariable Long gameId) {
        gameService.deleteGameById(gameId);
    }

}
