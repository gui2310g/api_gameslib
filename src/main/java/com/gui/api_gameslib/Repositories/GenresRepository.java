package com.gui.api_gameslib.Repositories;

import com.gui.api_gameslib.entities.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface GenresRepository extends JpaRepository<Genres, Integer> {
    Optional<Genres> findByName(String name);
}
