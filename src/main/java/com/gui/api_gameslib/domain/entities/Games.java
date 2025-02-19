package com.gui.api_gameslib.domain.entities;

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

    @Column(name = "slug", nullable = false)
    private String slug;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "released", nullable = false)
    private String released;

    @Column(name = "background_image", nullable = false)
    private String background_image;

    @Column(name = "image_logo", nullable = false)
    private String image_logo;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @ManyToMany
    @JoinTable(
            name = "game_platforms",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private List<Platforms> platforms;

    @ManyToMany
    @JoinTable(
            name = "game_esrbratings",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "rating_id")
    )
    private List<EsrbRating> esrbRatings;

    @ManyToMany
    @JoinTable(
            name = "game_screenshots",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "screenshot_id")
    )
    private List<Screenshots> Screenshots;

    @ManyToMany
    @JoinTable(
            name = "game_genres",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genres> Genres;

    @ManyToMany
    @JoinTable(
            name = "game_developers",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
    private List<Publishers> publishers;
}
