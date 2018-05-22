package com.mink.demo.bowlingapi.api.rest;


import com.mink.demo.bowlingapi.service.ScorecardService;
import com.mink.demo.bowlingapi.service.dto.ScorecardResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = {"/v1/game/{gameId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Scorecards", description = "Operations to manage Scorecards")
public class ScorecardResource {

    private final ScorecardService scorecardService;

    @Autowired
    public ScorecardResource(ScorecardService scorecardService) {
        this.scorecardService = scorecardService;
    }

    @GetMapping("/scorecards")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all scorecards for a game")
    public List<ScorecardResponse> getScorecardsByGameId(@PathVariable Long gameId) {
        return scorecardService.getScorecardsByGameId(gameId);
    }

    @GetMapping("/scorecards/{scorecardId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Lookup a scorecard for a game by its id")
    public ScorecardResponse getScorecardByGameIdById(@PathVariable Long gameId, @PathVariable Long scorecardId) {
        return scorecardService.getScorecardByGameIdById(gameId, scorecardId);
    }

    @DeleteMapping("/scorecards/{scorecardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Delete a scorecard from a game")
    public void deleteScorecardByGameIdById(@PathVariable Long gameId, @PathVariable Long scorecardId) {
        scorecardService.deleteScorecardGameIdById(gameId, scorecardId);
    }

}
