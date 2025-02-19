package com.gui.api_gameslib.dto.user;

import lombok.Data;

@Data
public class UserRequest {
    private final String username;
    private final String email;
    private final String password;
}
