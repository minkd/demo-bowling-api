package com.mink.demo.bowlingapi.service.mapper;

import com.mink.demo.bowlingapi.model.entities.Shot;
import com.mink.demo.bowlingapi.service.dto.ShotRequest;
import com.mink.demo.bowlingapi.service.dto.ShotResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ShotMapper {

    Shot shotRequestToShot(ShotRequest shotRequest);

    ShotResponse shotToShotResponse(Shot shot);

    List<ShotResponse> shotsToShotResponses (List<Shot> shots);
}
