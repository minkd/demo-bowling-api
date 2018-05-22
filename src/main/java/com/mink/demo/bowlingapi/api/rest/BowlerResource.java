package com.mink.demo.bowlingapi.api.rest;


import com.mink.demo.bowlingapi.service.BowlerService;
import com.mink.demo.bowlingapi.service.dto.BowlerRequest;
import com.mink.demo.bowlingapi.service.dto.BowlerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(path = {"/v1/game/{gameId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Bowlers", description = "Operations to manage Bowlers")
public class BowlerResource {

    private final BowlerService bowlerService;

    @Autowired
    public BowlerResource(BowlerService bowlerService) {
        this.bowlerService = bowlerService;
    }

    @GetMapping("/bowler")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all bowlers for a game")
    public Set<BowlerResponse> getBowlersByGameId(@PathVariable Long gameId) {
        return bowlerService.getBowlersByGameId(gameId);
    }

    @PostMapping("/bowler")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a new bowler to a game")
    public Set<BowlerResponse> addBowlerToGame(@PathVariable Long gameId, @Valid @RequestBody BowlerRequest bowlerRequest) {
        return bowlerService.addBowlerToGame(gameId, bowlerRequest);
    }

    @GetMapping("/bowler/{bowlerId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Lookup a bowler for a game by its id")
    public BowlerResponse getBowlerByGameIdById(@PathVariable Long gameId, @PathVariable Long bowlerId) {
        return bowlerService.getBowlerByGameIdById(gameId, bowlerId);
    }

    @DeleteMapping("/bowler/{bowlerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete a bowler from a game")
    public void deleteBowlerByGameIdById(@PathVariable Long gameId, @PathVariable Long bowlerId) {
        bowlerService.deleteBowlerByGameIdById(gameId, bowlerId);
    }

}
