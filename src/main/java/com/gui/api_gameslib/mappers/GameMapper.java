package com.gui.api_gameslib.mappers;

import com.gui.api_gameslib.dto.GameRequest;
import com.gui.api_gameslib.entities.Games;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {
    Games toEntity(GameRequest gameRequest);

    GameRequest toDto(Games games);
}
