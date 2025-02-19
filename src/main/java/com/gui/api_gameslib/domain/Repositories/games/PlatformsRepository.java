package com.gui.api_gameslib.domain.Repositories.games;

import com.gui.api_gameslib.domain.entities.Platforms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlatformsRepository extends JpaRepository<Platforms, Integer> {
    Optional<Platforms> findByName(String name);
}
