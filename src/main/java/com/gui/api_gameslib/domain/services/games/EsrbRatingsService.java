package com.gui.api_gameslib.domain.services.games;

import com.gui.api_gameslib.domain.entities.EsrbRating;
import com.gui.api_gameslib.domain.entities.Games;
import com.gui.api_gameslib.domain.Repositories.games.EsrbRatingsRepository;
import com.gui.api_gameslib.domain.Repositories.games.GamesRepository;
import com.gui.api_gameslib.domain.exceptions.GamesException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EsrbRatingsService {
    private final GamesRepository gamesRepository;

    private final EsrbRatingsRepository esrbRatingsRepository;

    public EsrbRating AddEsrbRatings(EsrbRating esrbRating) {
        if (esrbRatingsRepository.findByName(esrbRating.getName()).isPresent())
            throw new GamesException("this rating is already added");

        if(esrbRatingsRepository.count() >= 6) throw new GamesException("All the ratings are added");

        return esrbRatingsRepository.save(esrbRating);
    }

    public EsrbRating addEsrbRatingsToGame(Integer esrbRatingsId, Integer gameId) {
        Games game = gamesRepository.findById(gameId)
                .orElseThrow(() -> new GamesException("Game not found with ID: " + gameId));

        EsrbRating rating = esrbRatingsRepository.findById(esrbRatingsId)
                .orElseThrow(() -> new GamesException("Esrb Rating not found with id: " + esrbRatingsId));

        if (game.getEsrbRatings().stream().anyMatch(p -> p.getId().equals(esrbRatingsId)))
            throw new GamesException("This rating is already added in this game");

        if (!game.getEsrbRatings().isEmpty()) throw new GamesException("The game only needs one rating");

        game.getEsrbRatings().add(rating);
        gamesRepository.save(game);

        return rating;
    }

    public List<EsrbRating> findAllEsrbRatings() {
        return esrbRatingsRepository.findAll().stream().toList();
    }

    public List<EsrbRating> findEsrbRatingbyGameId(Integer gameId) {
        Games game = gamesRepository.findById(gameId)
               .orElseThrow(() -> new GamesException("Game not found with ID: " + gameId));

        return game.getEsrbRatings().stream().toList();
    }
}
