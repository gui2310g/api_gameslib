package com.gui.api_gameslib.dto.Game;

import lombok.Data;

@Data
public class GameRequest {
    private Integer id;
    private String slug;
    private String name;
    private String description;
    private String released;
    private String background_image;
    private String image_logo;
    private Integer rating;

}
