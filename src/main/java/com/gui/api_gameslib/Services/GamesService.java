package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.Models.Games;
import com.gui.api_gameslib.Repositories.GamesRepository;
import com.gui.api_gameslib.Repositories.UsersRepository;
import com.gui.api_gameslib.exceptions.GamesException;
import com.gui.api_gameslib.exceptions.UserException;
import lombok.AllArgsConstructor;
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
        return gamesRepository.findAll();
    }

    public Games FindGamesById(Integer id) throws GamesException {
        return gamesRepository.findById(id)
                .orElseThrow(() -> new GamesException("Can't find a game with this id"));
    }

    public List<Games> SearchGames(String name) throws GamesException {
        return gamesRepository.findByNameContainingIgnoreCase(name);
    }
}
