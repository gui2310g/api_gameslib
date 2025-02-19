package com.gui.api_gameslib.controller.games;

import com.gui.api_gameslib.domain.entities.EsrbRating;
import com.gui.api_gameslib.domain.services.games.EsrbRatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class EsrbRatingsController {

    @Autowired
    private EsrbRatingsService esrbRatingsService;

    @PostMapping
    public ResponseEntity<EsrbRating> AddEsrbRatings(@RequestBody EsrbRating esrbRating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(esrbRatingsService.AddEsrbRatings(esrbRating));
    }

    @PostMapping("/add/{gameId}/{EsrbRatingsId}")
    public ResponseEntity<EsrbRating> AddEsrbRatingsToGame(
            @PathVariable Integer EsrbRatingsId,
            @PathVariable Integer gameId
    ) {;
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(esrbRatingsService.addEsrbRatingsToGame(EsrbRatingsId, gameId));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<EsrbRating>> findAllEsrbRatings() {
        return ResponseEntity.ok(esrbRatingsService.findAllEsrbRatings());
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<EsrbRating>> findEsrbRatingByGameId(@PathVariable Integer gameId) {
        return ResponseEntity.ok(esrbRatingsService.findEsrbRatingbyGameId(gameId));
    }
}
