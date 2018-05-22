package com.mink.demo.bowlingapi.service.mapper;

import com.mink.demo.bowlingapi.model.entities.Game;
import com.mink.demo.bowlingapi.service.dto.GameRequest;
import com.mink.demo.bowlingapi.service.dto.GameResponse;
import org.mapstruct.Mapper;

@Mapper
public interface GameMapper {

    Game gameRequestToGame(GameRequest gameRequest);

    GameResponse gameToGameResponse(Game game);

}
