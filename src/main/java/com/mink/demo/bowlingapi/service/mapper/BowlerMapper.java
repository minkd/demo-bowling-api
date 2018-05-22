package com.mink.demo.bowlingapi.service.mapper;

import com.mink.demo.bowlingapi.model.entities.Bowler;
import com.mink.demo.bowlingapi.service.dto.BowlerRequest;
import com.mink.demo.bowlingapi.service.dto.BowlerResponse;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface BowlerMapper {

    Bowler bowlerRequestToBowler(BowlerRequest bowlerRequest);

    BowlerResponse bowlerToBowlerResponse(Bowler bowler);

    Set<BowlerResponse> bowlersToBowlerResponses(Set<Bowler> bowlers);
}
