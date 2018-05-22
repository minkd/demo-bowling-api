package com.mink.demo.bowlingapi.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GameResponse extends GameRequest {
    private long id;
}
