package com.gui.api_gameslib.dto;

import lombok.Data;

@Data
public class GameRequest {
    private final String slug;
    private final String name;
    private final String description;
    private final String released;
    private final String background_image;
    private final String image_logo;
    private final Integer rating;
}
