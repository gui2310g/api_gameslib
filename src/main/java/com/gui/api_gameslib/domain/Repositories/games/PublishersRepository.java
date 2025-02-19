package com.gui.api_gameslib.domain.Repositories.games;

import com.gui.api_gameslib.domain.entities.Publishers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublishersRepository extends JpaRepository<Publishers, Integer> {
    Optional<Publishers> findByName(String name);
}
