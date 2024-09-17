package com.gui.api_gameslib.Repositories;

import com.gui.api_gameslib.Models.Publishers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublishersRepository extends JpaRepository<Publishers, Integer> {
    Optional<Publishers> findByName(String name);
}
