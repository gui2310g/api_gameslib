package com.gui.api_gameslib.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "publishers")
public class Publishers {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "games_count")
    private Integer games_count;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Games game;
}
