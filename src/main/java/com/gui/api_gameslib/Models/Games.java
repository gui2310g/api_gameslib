package com.gui.api_gameslib.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "game_platforms",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private Set<Platforms> platforms;

    @ManyToMany
    @JoinTable(
            name = "game_esrbratings",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "rating_id")
    )
    private Set<EsrbRating> esrbRatings;

    @ManyToMany
    @JoinTable(
            name = "game_screenshots",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "screenshot_id")
    )
    private Set<Screenshots> Screenshots;

    @ManyToMany
    @JoinTable(
            name = "game_genres",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genres> Genres;

    @ManyToMany
    @JoinTable(
            name = "game_developers",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
    private Set<Publishers> publishers;
}
