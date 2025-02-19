package com.gui.api_gameslib.domain.services.games;

import com.gui.api_gameslib.domain.entities.Games;
import com.gui.api_gameslib.domain.entities.Platforms;

import com.gui.api_gameslib.domain.Repositories.games.GamesRepository;
import com.gui.api_gameslib.domain.Repositories.games.PlatformsRepository;
import com.gui.api_gameslib.domain.exceptions.GamesException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlatformsService {
    private final PlatformsRepository platformsRepository;

    private final GamesRepository gamesRepository;

    public Platforms addPlatform(Platforms platforms) {
        if(platformsRepository.findByName(platforms.getName()).isPresent())
            throw new GamesException("Platform with this name already exists");

        return platformsRepository.save(platforms);
    }

    public Platforms addPlatformToGame(Integer platformsId, Integer gameId) {
        Games game = gamesRepository.findById(gameId)
                .orElseThrow(() -> new GamesException("Game not found ID: " + gameId));

        Platforms platform = platformsRepository.findById(platformsId)
                .orElseThrow(() -> new GamesException("Platform not found with id: " + platformsId));

        if (game.getPlatforms().stream().anyMatch(p -> p.getId().equals(platformsId)))
            throw new Error("This platform is already added in this game");

        game.getPlatforms().add(platform);
        gamesRepository.save(game);

        return platform;
    }

    public List<Platforms> findAllPlatforms()  {
        return platformsRepository.findAll().stream().toList();
    }

    public List<Platforms> findAllPlatformsByGameId(Integer gameId) throws GamesException {
        Games game = gamesRepository.findById(gameId)
               .orElseThrow(() -> new GamesException("Game not found with ID: " + gameId));

        return game.getPlatforms().stream().toList();
    }
}
