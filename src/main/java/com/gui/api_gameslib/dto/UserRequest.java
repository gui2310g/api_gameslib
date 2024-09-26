package com.gui.api_gameslib.dto;

import lombok.Data;

@Data
public class UserRequest {
    private final String name;
    private final String username;
    private final String email;
    private final String password;
}
