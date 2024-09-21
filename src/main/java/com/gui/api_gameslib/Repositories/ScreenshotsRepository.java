package com.gui.api_gameslib.Repositories;

import com.gui.api_gameslib.entities.Screenshots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScreenshotsRepository extends JpaRepository<Screenshots, Integer> {
    Optional<Screenshots> findByUrl(String screenshotUrl);
}
