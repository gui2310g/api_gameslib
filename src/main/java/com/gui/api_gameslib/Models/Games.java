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
    @Column(name = "description")
    private String description;
    @Column(name = "released")
    private String released;
    @Column(name = "background_image")
    private String background_image;
    @Column(name = "background_image_additional")
    private String background_image_additional;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "screenshots_count")
    private Integer screenshots_count;
    @Column(name = "movies_count")
    private Integer movies_count;
    @Column(name = "platforms")
    private List<String> platforms;
}
