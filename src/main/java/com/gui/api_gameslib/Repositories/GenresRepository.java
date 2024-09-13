package com.gui.api_gameslib.Repositories;

import com.gui.api_gameslib.Models.Genres;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GenresRepository extends CrudRepository<Genres, Integer> {
    Optional<Genres> findById(Integer id);

    List<Genres> findAll();
}
