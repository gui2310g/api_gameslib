package com.gui.api_gameslib.Repositories;

import com.gui.api_gameslib.Models.Games;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GamesRepository extends CrudRepository<Games, String> {

    Optional<Games> findById(Integer id);

    /* Optional<Games> findGamesByUser(Integer userId); */

    List<Games> findByNameContainingIgnoreCase(String name);

     List<Games> findPublishersById(Integer publishersId);

    List<Games> findEsrbRatingsById(Integer esrbRatingsId);

    List<Games> findGenresById(Integer genresId);

    List<Games> findPlatformsById(Integer platformsId);

    List<Games> findScreenshotsById(Integer ScreenshotsId);
}