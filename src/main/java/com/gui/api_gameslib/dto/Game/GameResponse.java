package com.gui.api_gameslib.dto.Game;

import com.gui.api_gameslib.domain.entities.*;
import lombok.Data;

import java.util.List;

@Data
public class GameResponse {
    private Integer id;
    private String slug;
    private String name;
    private String description;
    private String released;
    private String background_image;
    private String image_logo;
    private Integer rating;
    private List<Platforms> platforms;
    private List<EsrbRating> esrbRatings;
    private List<com.gui.api_gameslib.domain.entities.Screenshots> Screenshots;
    private List<com.gui.api_gameslib.domain.entities.Genres> Genres;
    private List<Publishers> publishers;
}
