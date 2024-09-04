package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.Models.Games;
import com.gui.api_gameslib.Repositories.GamesRepository;
import com.gui.api_gameslib.Repositories.UsersRepository;
import com.gui.api_gameslib.exceptions.GamesException;
import com.gui.api_gameslib.exceptions.UserException;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GamesService {

    private final GamesRepository gamesRepository;

    public Games AddGames(Games games) throws GamesException {
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
        return gamesRepository.findByNameContainingIgnoreCase(name);
    }
}
