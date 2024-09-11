package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.Models.Games;
import com.gui.api_gameslib.Models.Platforms;

import com.gui.api_gameslib.Repositories.GamesRepository;
import com.gui.api_gameslib.Repositories.PlatformsRepository;
import com.gui.api_gameslib.exceptions.GamesException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlatformsService {

    private final PlatformsRepository platformsRepository;

    private final GamesRepository gamesRepository;

    public Platforms addPlatform(Platforms platforms) throws GamesException{

        if(platformsRepository.findByName(platforms.getName()).isPresent())
            throw new GamesException("Platform with this name already exists");

        return platformsRepository.save(platforms);
    }

    public Platforms addPlatformToGame(String platformName, Integer gameId) throws GamesException {
        Games game = gamesRepository.findById(gameId)
                .orElseThrow(() -> new GamesException("Game not found with ID: " + gameId));

        Platforms platform = platformsRepository.findByName(platformName)
                .orElseThrow(() -> new GamesException("Platform not found with name: " + platformName));

        if (game.getPlatforms().stream().anyMatch(p -> p.getName().equals(platformName)))
            throw new Error("This platform is already added in this game");

        game.getPlatforms().add(platform);
        gamesRepository.save(game);

        return platform;
    }

    public Iterable<Platforms> findAllPlatforms() throws GamesException {
        Iterable<Platforms> platforms = platformsRepository.findAll();

        if(!platforms.iterator().hasNext()) throw new GamesException("There is no registered platforms");

        return platforms;
    }
}
