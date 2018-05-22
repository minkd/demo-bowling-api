package com.mink.demo.bowlingapi.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ScorecardResponse {
    private String bowlerName;

    private List<FrameResponse> frameResponses;

    private int total;

}
