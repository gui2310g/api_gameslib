package com.gui.api_gameslib.mappers;

import com.gui.api_gameslib.dto.UserRequest;
import com.gui.api_gameslib.entities.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Users toEntity(UserRequest userRequest);

    UserRequest toDto(Users users);
}
