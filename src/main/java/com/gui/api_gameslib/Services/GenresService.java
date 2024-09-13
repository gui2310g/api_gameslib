package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.Models.Genres;
import com.gui.api_gameslib.Repositories.GenresRepository;
import com.gui.api_gameslib.exceptions.GamesException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenresService {
    private final GenresRepository genresRepository;

    private Genres addGenres(Genres genres) throws GamesException {
        if(genresRepository.findByName(genres.getName()).isPresent())
            throw new GamesException("This genre has already been added");

        return genresRepository.save(genres);
    }
}
