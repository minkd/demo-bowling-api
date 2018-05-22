package com.mink.demo.bowlingapi.service.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ShotRequest {
    private int pins;
}
