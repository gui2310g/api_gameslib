package com.gui.api_gameslib.Repositories;

import com.gui.api_gameslib.Models.Games;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface GamesRepository extends JpaRepository<Games, Integer> {
    Optional<Games> findByName(String name);

    List<Games> findByPlatformsId(Integer platformsId);

    List<Games> findByNameContainingIgnoreCase(String name);
}