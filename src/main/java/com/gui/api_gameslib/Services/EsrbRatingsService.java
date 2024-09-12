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

    public EsrbRating AddEsrbRatings(EsrbRating esrbRating) throws GamesException {

        if (esrbRatingsRepository.findByName(esrbRating.getName()).isPresent())
            throw new GamesException("this rating is already added");

        if(esrbRatingsRepository.count() >= 6) throw new GamesException("All the ratings are added");

        return esrbRatingsRepository.save(esrbRating);
    }

    public EsrbRating addEsrbRatingsToGame(String esrbRatingName, Integer gameId) throws GamesException {
        Games game = gamesRepository.findById(gameId)
                .orElseThrow(() -> new GamesException("Game not found with ID: " + gameId));

        EsrbRating rating = esrbRatingsRepository.findByName(esrbRatingName)
                .orElseThrow(() -> new GamesException("EsrbRating Can't found with this name" + esrbRatingName));

        if (game.getEsrbRatings().stream().anyMatch(p -> p.getName().equals(esrbRatingName)))
            throw new GamesException("This rating is already added in this game");

        if (game.getEsrbRatings().size() > 1) throw new GamesException("The game only needs one rating");

        game.getEsrbRatings().add(rating);
        gamesRepository.save(game);

        return rating;
    }

    public Iterable<EsrbRating> findAllEsrbRatings() throws GamesException {
        Iterable<EsrbRating> esrbRatings = esrbRatingsRepository.findAll();

        if (!esrbRatings.iterator().hasNext()) throw new GamesException("There is no registered ratings");

        return esrbRatings;
    }
}
