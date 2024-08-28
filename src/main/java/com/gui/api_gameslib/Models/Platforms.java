package com.gui.api_gameslib.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "platforms")
public class Platforms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "slug")
    private String slug;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Games game;

    @Column(name = "released_at")
    private String releasedAt;

}
