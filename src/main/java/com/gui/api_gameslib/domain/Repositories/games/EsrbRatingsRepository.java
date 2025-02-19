package com.gui.api_gameslib.domain.Repositories.games;

import com.gui.api_gameslib.domain.entities.EsrbRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EsrbRatingsRepository  extends JpaRepository<EsrbRating, Integer> {
    Optional<EsrbRating> findByName(String name);
}
