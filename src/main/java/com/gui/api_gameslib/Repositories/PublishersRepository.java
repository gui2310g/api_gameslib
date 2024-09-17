package com.gui.api_gameslib.Repositories;

import com.gui.api_gameslib.Models.Publishers;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PublishersRepository extends CrudRepository<Publishers, Integer> {

    Optional<Publishers> findById(Integer id);
}
