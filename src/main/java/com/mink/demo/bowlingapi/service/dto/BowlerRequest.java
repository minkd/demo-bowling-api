package com.mink.demo.bowlingapi.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BowlerRequest {

    @NotNull(message = "Bowler name is required")
    private String name;

}
