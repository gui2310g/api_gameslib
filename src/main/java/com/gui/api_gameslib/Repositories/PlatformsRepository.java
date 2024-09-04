package com.gui.api_gameslib.Repositories;

import com.gui.api_gameslib.Models.Platforms;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlatformsRepository extends CrudRepository<Platforms, String> {

    Optional <Platforms> findById(Integer id);


    Optional<Platforms> findByName(String name);
}
