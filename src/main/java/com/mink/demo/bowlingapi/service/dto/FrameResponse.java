package com.mink.demo.bowlingapi.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class FrameResponse {
    private List<ShotResponse> shots;

    private int score;

    private int frame;
}
