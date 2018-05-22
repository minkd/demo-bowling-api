package com.mink.demo.bowlingapi.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ShotResponse extends ShotRequest{
    private int score;
}
