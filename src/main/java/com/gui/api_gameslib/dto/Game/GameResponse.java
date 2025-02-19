package com.gui.api_gameslib.dto.Game;

import com.gui.api_gameslib.entities.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

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
    private List<Screenshots> Screenshots;
    private List<Genres> Genres;
    private List<Publishers> publishers;
}
