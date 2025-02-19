package com.gui.api_gameslib.dto.user;

import com.gui.api_gameslib.entities.Games;
import lombok.Data;

import java.util.Set;

@Data
public class UserResponse {
    private final String username;
    private final String email;
    private final String password;
    private Set<Games> WishlistGames;
}
