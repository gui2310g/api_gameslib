package com.gui.api_gameslib.Repositories;

import com.gui.api_gameslib.Models.Screenshots;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScreenshotsRepository extends CrudRepository<Screenshots, Integer> {
    Optional<Screenshots> findById(Integer id);

    Optional<Screenshots> findByUrl(String screenshotUrl);

    List<Screenshots> findAll();
}
