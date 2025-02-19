package com.gui.api_gameslib.domain.services.games;

import com.gui.api_gameslib.domain.entities.Games;
import com.gui.api_gameslib.domain.entities.Genres;
import com.gui.api_gameslib.domain.Repositories.games.GamesRepository;
import com.gui.api_gameslib.domain.Repositories.games.GenresRepository;
import com.gui.api_gameslib.domain.exceptions.GamesException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenresService {
    private final GenresRepository genresRepository;

    private final GamesRepository gamesRepository;

    public Genres addGenres(Genres genre) {
        if(genresRepository.findByName(genre.getName()).isPresent()) throw new GamesException("has already been added");

        return genresRepository.save(genre);
    }

    public Genres addGenresToGame(Integer genresId, Integer gameId) {
        Games game = gamesRepository.findById(gameId)
                .orElseThrow(() -> new GamesException("Game ID not found: " + gameId));

        Genres genre = genresRepository.findById(genresId).orElseThrow(
                () -> new GamesException("Genre not found with id: " + genresId));

        if (game.getGenres().stream().anyMatch(p -> p.getId().equals(genresId)))
            throw new GamesException("This genre is already added in this game");

        game.getGenres().add(genre);
        gamesRepository.save(game);

        return genre;
    }

    public List<Genres> findAllGenres() {
        return genresRepository.findAll().stream().toList();
    }

    public List<Genres> findAllGenresByGameId(Integer gameId) {
        Games game = gamesRepository.findById(gameId)
                .orElseThrow(() -> new GamesException("Game id not found: " + gameId));

        return game.getGenres().stream().toList();
    }
}
