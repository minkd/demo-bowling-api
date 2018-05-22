package com.mink.demo.bowlingapi.service.dto;


import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
public class GameRequest {

    @NotNull(message = "Name is required")
    private String name;

    @NotNull(message = "Lane is required")
    @Range(min = 1, max = 20, message = "Lane must be between 1 and 20")
    private int lane;
}
