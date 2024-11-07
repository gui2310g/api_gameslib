package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.entities.Games;
import com.gui.api_gameslib.entities.Genres;
import com.gui.api_gameslib.Repositories.GamesRepository;
import com.gui.api_gameslib.Repositories.GenresRepository;
import com.gui.api_gameslib.exceptions.GamesException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<Genres> findAllGenres(int page, int size) throws GamesException {
        Page<Genres> genre = genresRepository.findAll(PageRequest.of(page, size));

        if (page >= genre.getTotalPages()) throw new GamesException("Page number out of bounds");

        if(genre.isEmpty()) throw new GamesException("There is no registered genres");

        return genre;
    }

    public List<Genres> findAllGenresByGameId(Integer gameId) throws GamesException {
        Games game = gamesRepository.findById(gameId)
                .orElseThrow(() -> new GamesException("There is no game with this id: " + gameId));

        if(game.getGenres().isEmpty()) throw new GamesException("This game doesn't have a genre");

        return game.getGenres().stream().toList();
    }
}
