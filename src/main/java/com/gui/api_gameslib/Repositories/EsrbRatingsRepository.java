package com.gui.api_gameslib.Repositories;

import com.gui.api_gameslib.Models.EsrbRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EsrbRatingsRepository  extends JpaRepository<EsrbRating, Integer> {
    Optional<EsrbRating> findByName(String name);
}
