package com.gui.api_gameslib.Repositories;

import com.gui.api_gameslib.Models.Games;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GamesRepository extends CrudRepository<Games, String> {

    Optional<Games> findById(Integer id);

    Optional<Games> findByName(String name);

    List<Games> findByNameContainingIgnoreCase(String name);

}