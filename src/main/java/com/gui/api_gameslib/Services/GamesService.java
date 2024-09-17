package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.Models.Games;
import com.gui.api_gameslib.Models.Platforms;
import com.gui.api_gameslib.Repositories.GamesRepository;
import com.gui.api_gameslib.Repositories.PlatformsRepository;
import com.gui.api_gameslib.exceptions.GamesException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GamesService {
    private final GamesRepository gamesRepository;

    private final PlatformsRepository platformsRepository;
    public Games AddGames(Games games) throws GamesException {
        if(gamesRepository.findByName(games.getName()).isPresent())
            throw new GamesException("This game already exists");

        return gamesRepository.save(games);
    }

    public List<Games> FindAllGames() throws GamesException {
        List<Games> games = gamesRepository.findAll();

        if (games.isEmpty()) throw new GamesException("There is no registered games");

        return games;
    }

    public Games FindGamesById(Integer id) throws GamesException {
        return gamesRepository.findById(id).orElseThrow(() -> new GamesException("Can't find a game with this id"));
    }

    public List<Games> SearchGames(String name) throws GamesException {
        List<Games> games = gamesRepository.findByNameContainingIgnoreCase(name);

        if(games == null || games.isEmpty()) throw new GamesException("There is no registered games with this name");

        return games;
    }

    public List<Games> findGamesByPlatformsId(Integer platformsId) throws GamesException {
        Platforms platform = platformsRepository.findById(platformsId)
                .orElseThrow(() -> new GamesException("Platform not found with Id: " + platformsId));

        List<Games> games = gamesRepository.findByPlatformsId(platformsId);

        if (games.isEmpty()) throw new GamesException("No games found for this platform");

        return games;
    }
}
