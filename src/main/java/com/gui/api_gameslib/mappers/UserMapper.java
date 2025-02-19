package com.gui.api_gameslib.mappers;

import com.gui.api_gameslib.dto.user.UserRequest;
import com.gui.api_gameslib.dto.user.UserResponse;
import com.gui.api_gameslib.entities.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Users toEntity(UserRequest userRequest);

    UserResponse toDto(Users users);
}
