package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.Models.Games;
import com.gui.api_gameslib.Models.Platforms;

import com.gui.api_gameslib.Repositories.GamesRepository;
import com.gui.api_gameslib.Repositories.PlatformsRepository;
import com.gui.api_gameslib.exceptions.PlatformsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;


@Service
@AllArgsConstructor
public class PlatformsService {

    private final PlatformsRepository platformsRepository;

    private final GamesRepository gamesRepository;

    public Platforms addPlatform(Platforms platforms) {
        return platformsRepository.save(platforms);
    }

    public Platforms addPlatformToGame(String platformName, Integer gameId) throws PlatformsException {
        Games game = gamesRepository.findById(gameId)
                .orElseThrow(() -> new PlatformsException("Game not found with ID: " + gameId));

        Platforms platform = platformsRepository.findByName(platformName)
                .orElseThrow(() -> new PlatformsException("Platform not found with name: " + platformName));

        game.getPlatforms().add(platform);
        gamesRepository.save(game);

        return platform;
    }


    public Iterable<Platforms> findAllPlatforms() throws PlatformsException {
        Iterable<Platforms> platforms = platformsRepository.findAll();

        if(platforms.iterator().hasNext()) throw new PlatformsException("There is no registered platforms");

        return platforms;
    }

    public Platforms findPlatformsById(Integer id) throws PlatformsException {
        return platformsRepository.findById(id).orElseThrow(()
                -> new PlatformsException("Can't find a platform with this id"));
    }


}
