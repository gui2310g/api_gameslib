package com.gui.api_gameslib.Repositories;

import com.gui.api_gameslib.Models.EsrbRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EsrbRatingsRepository  extends CrudRepository<EsrbRating, String> {
    Optional<EsrbRating> findById(Integer id);

    List<EsrbRating> findAll();

    Optional<EsrbRating> findByName(String name);
}
