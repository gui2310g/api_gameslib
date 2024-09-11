package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.Models.Games;
import com.gui.api_gameslib.Repositories.GamesRepository;
import com.gui.api_gameslib.exceptions.GamesException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GamesService {

    private final GamesRepository gamesRepository;

    public Games AddGames(Games games) throws GamesException {

        if(gamesRepository.findByName(games.getName()).isPresent())
            throw new Error("This game already exists");

        return gamesRepository.save(games);
    }

    public Iterable<Games> FindAllGames() throws GamesException {
        Iterable<Games> games = gamesRepository.findAll();

        if (!games.iterator().hasNext()) throw new GamesException("There is no registered games");

        return games;
    }

    public Games FindGamesById(Integer id) throws GamesException {
        return gamesRepository.findById(id)
                .orElseThrow(() -> new GamesException("Can't find a game with this id"));
    }

    public List<Games> SearchGames(String name) throws GamesException {
        List<Games> games = gamesRepository.findByNameContainingIgnoreCase(name);

        if (games == null || games.isEmpty())
            throw new GamesException("There is no registered games with this name");

        return games;
    }
}
