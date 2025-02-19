package com.gui.api_gameslib.domain.Repositories.games;

import com.gui.api_gameslib.domain.entities.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface GamesRepository extends JpaRepository<Games, Integer> {
    Optional<Games> findByName(String name);

    List<Games> findByNameContainingIgnoreCase(String name);
}