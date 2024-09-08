package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.Models.EsrbRating;
import com.gui.api_gameslib.Models.Games;
import com.gui.api_gameslib.Repositories.EsrbRatingsRepository;
import com.gui.api_gameslib.Repositories.GamesRepository;
import com.gui.api_gameslib.exceptions.GamesException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EsrbRatingsService {

    private GamesRepository gamesRepository;

    private EsrbRatingsRepository esrbRatingsRepository;

    public EsrbRating AddEsrbRatings(EsrbRating esrbRating) {
        return esrbRatingsRepository.save(esrbRating);
    }

    public EsrbRating addEsrbRatingsToGame(String esrbRatingName, Integer gameId) throws GamesException {
        Games game = gamesRepository.findById(gameId)
                .orElseThrow(() -> new GamesException("Game not found with ID: " + gameId));

        EsrbRating rating = esrbRatingsRepository.findByName(esrbRatingName)
                .orElseThrow(() -> new GamesException("EsrbRating Can't found with this name" + esrbRatingName));

        game.getEsrbRatings().add(rating);
        gamesRepository.save(game);

        return rating;
    }

    public Iterable<EsrbRating> findAllEsrbRatings() throws GamesException {
        Iterable<EsrbRating> esrbRatings = esrbRatingsRepository.findAll();

        if(!esrbRatings.iterator().hasNext()) throw new GamesException("There is no registered ratings");

        return esrbRatings;
    }
}
