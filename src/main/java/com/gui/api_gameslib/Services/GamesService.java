package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.entities.Games;
import com.gui.api_gameslib.entities.Platforms;
import com.gui.api_gameslib.entities.Publishers;
import com.gui.api_gameslib.Repositories.GamesRepository;
import com.gui.api_gameslib.Repositories.PlatformsRepository;
import com.gui.api_gameslib.Repositories.PublishersRepository;
import com.gui.api_gameslib.exceptions.GamesException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GamesService {
    private final GamesRepository gamesRepository;

    private final PlatformsRepository platformsRepository;

    private final PublishersRepository publishersRepository;

    public Games AddGames(Games games) throws GamesException {
        if(gamesRepository.findByName(games.getName()).isPresent())
            throw new GamesException("This game already exists");

        return gamesRepository.save(games);
    }

    public Page<Games> FindAllGames(int page, int size) throws GamesException {
        Page<Games> games = gamesRepository.findAll(PageRequest.of(page, size));

        if (page >= games.getTotalPages()) throw new GamesException("Page number out of bounds");

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
                .orElseThrow(() -> new GamesException("No games found with this platform id"));

        List<Games> games = gamesRepository.findByPlatformsId(platformsId);

        if (games.isEmpty()) throw new GamesException("No games found for this platform");

        return games;
    }

    public List<Games> findGamesByPublishersId(Integer publishersId) throws GamesException {
        Publishers publishers = publishersRepository.findById(publishersId)
                .orElseThrow(() -> new GamesException("No games found with this publisher id"));

        List<Games> games = gamesRepository.findByPublishersId(publishersId);

        if(games.isEmpty()) throw new GamesException("No games found for this publisher");

        return games;
    }
}
