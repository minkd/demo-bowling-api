package com.mink.demo.bowlingapi.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BowlerResponse extends BowlerRequest {
    private long id;
}
