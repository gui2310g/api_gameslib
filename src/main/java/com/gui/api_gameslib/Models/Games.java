package com.gui.api_gameslib.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "games")
public class Games {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "slug")
    private String slug;
    @Column(name = "name")
    private String name;
    @Column(name = "description", length = 1000)
    private String description;
    @Column(name = "released")
    private String released;
    @Column(name = "background_image")
    private String background_image;
    @Column(name = "image_logo")
    private String image_logo;
    @Column(name = "rating")
    private Integer rating;
    
    @OneToMany(mappedBy = "game")
    private List<Platforms> platforms;

    @OneToMany(mappedBy = "game")
    private List<EsrbRating> esrbRatings;

    @OneToMany(mappedBy = "game")
    private List<Screenshots> Screenshots;

    @OneToMany(mappedBy = "game")
    private List<Genres> Genres;

    @OneToMany(mappedBy = "game")
    private List<Publishers> publishers;
}
