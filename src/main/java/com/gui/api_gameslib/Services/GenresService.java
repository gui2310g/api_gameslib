package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.Models.Games;
import com.gui.api_gameslib.Models.Genres;
import com.gui.api_gameslib.Repositories.GamesRepository;
import com.gui.api_gameslib.Repositories.GenresRepository;
import com.gui.api_gameslib.exceptions.GamesException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenresService {

    private final GenresRepository genresRepository;

    private final GamesRepository gamesRepository;

    public Genres addGenres(Genres genres) throws GamesException {
        if(genresRepository.findByName(genres.getName()).isPresent())
            throw new GamesException("This genre has already been added");

        return genresRepository.save(genres);
    }

    public Genres addGenresToGame(Integer genresId, Integer gameId) throws GamesException {
        Games game = gamesRepository.findById(gameId).orElseThrow(
                () -> new GamesException("Game not found with ID: " + gameId));

        Genres genre = genresRepository.findById(genresId).orElseThrow(
                () -> new GamesException("Genre not found with id: " + genresId));

        if (game.getGenres().stream().anyMatch(p -> p.getId().equals(genresId)))
            throw new GamesException("This genre is already added in this game");

        game.getGenres().add(genre);
        gamesRepository.save(game);

        return genre;
    }

    public List<Genres> findAllGenres() throws GamesException {
        List<Genres> genre = genresRepository.findAll();

        if(genre.isEmpty()) throw new GamesException("There is no registered genres");

        return genre;
    }
}
