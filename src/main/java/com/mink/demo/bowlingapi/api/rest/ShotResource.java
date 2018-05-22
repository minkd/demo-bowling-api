package com.mink.demo.bowlingapi.api.rest;


import com.mink.demo.bowlingapi.service.ShotService;

import com.mink.demo.bowlingapi.service.dto.ShotRequest;
import com.mink.demo.bowlingapi.service.dto.ShotResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import java.util.List;

@RestController
@RequestMapping(path = {"/v1/game/{gameId}/bowler/{bowlerId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Shots", description = "Operations to manage Shots")
public class ShotResource {

    private final ShotService shotService;

    @Autowired
    public ShotResource(ShotService shotService) {
        this.shotService = shotService;
    }

    @PostMapping("/shot")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add a new shot to a frame")
    public void addShotByGameIdByFrameId(
            @PathVariable long gameId,
            @PathVariable long bowlerId,
            @RequestBody ShotRequest shotRequest) {
        shotService.addShotToFrameByGameIdByBowlerId(gameId, bowlerId, shotRequest);
    }

}
