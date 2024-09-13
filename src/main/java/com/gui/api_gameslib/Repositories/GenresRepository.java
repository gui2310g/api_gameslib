package com.gui.api_gameslib.Repositories;

import com.gui.api_gameslib.Models.Genres;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface GenresRepository extends CrudRepository<Genres, Integer> {
    Optional<Genres> findById(Integer id);

    List<Genres> findAll();

    Optional<Genres> findByName(String name);
}
