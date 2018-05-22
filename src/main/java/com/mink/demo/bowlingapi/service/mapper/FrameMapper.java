package com.mink.demo.bowlingapi.service.mapper;

import com.mink.demo.bowlingapi.model.entities.Frame;
import com.mink.demo.bowlingapi.service.dto.FrameResponse;
import org.mapstruct.Mapper;

@Mapper
public interface FrameMapper {

    FrameResponse frameToFrameResponse(Frame frame);

}
