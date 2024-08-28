package com.gui.api_gameslib.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "screenshots")

public class Screenshots {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Games game;
}