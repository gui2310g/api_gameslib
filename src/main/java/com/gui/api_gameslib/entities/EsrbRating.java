package com.gui.api_gameslib.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "esrb_ratings")
public class EsrbRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "slug")
    private String slug;

    @Column(name = "name")
    private String name;

    public EsrbRating(){}
}