package com.gui.api_gameslib.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
    
    @ManyToMany
    @JoinTable(
            name = "users_wishlistGames",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    @JsonIgnoreProperties({
            "description", "released", "background_image",
            "image_logo", "rating", "platforms",
            "esrbRatings", "publishers", "genres", "screenshots"
    })
    private List<Games> WishlistGames;

    public Users() {}
}
