package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.Models.Games;
import com.gui.api_gameslib.Models.Platforms;

import com.gui.api_gameslib.Repositories.GamesRepository;
import com.gui.api_gameslib.Repositories.PlatformsRepository;
import com.gui.api_gameslib.exceptions.GamesException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Platforms addPlatformToGame(Integer platformsId, Integer gameId) throws GamesException {
        Games game = gamesRepository.findById(gameId)
                .orElseThrow(() -> new GamesException("Game not found with ID: " + gameId));

        Platforms platform = platformsRepository.findById(platformsId)
                .orElseThrow(() -> new GamesException("Platform not found with id: " + platformsId));

        if (game.getPlatforms().stream().anyMatch(p -> p.getId().equals(platformsId)))
            throw new Error("This platform is already added in this game");

        game.getPlatforms().add(platform);
        gamesRepository.save(game);

        return platform;
    }

    public Page<Platforms> findAllPlatforms(int page, int size) throws GamesException {
        Page<Platforms> platforms = platformsRepository.findAll(PageRequest.of(page, size));

        if (page >= platforms.getTotalPages()) throw new GamesException("Page number out of bounds");

        if(platforms.isEmpty()) throw new GamesException("There is no registered platforms");

        return platforms;
    }
}
