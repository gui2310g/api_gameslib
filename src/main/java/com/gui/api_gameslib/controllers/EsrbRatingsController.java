package com.gui.api_gameslib.controllers;

import com.gui.api_gameslib.entities.EsrbRating;
import com.gui.api_gameslib.Services.EsrbRatingsService;
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

    public ResponseEntity<EsrbRating> AddEsrbRatings(@RequestBody EsrbRating esrbRating) {
        EsrbRating createdRating = esrbRatingsService.AddEsrbRatings(esrbRating);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRating);
    }

    @PostMapping("/add/{gameId}/{EsrbRatingsId}")
    public ResponseEntity<EsrbRating> AddEsrbRatingsToGame(
            @PathVariable Integer EsrbRatingsId,
            @PathVariable Integer gameId
    ) {
        EsrbRating addRatings = esrbRatingsService.addEsrbRatingsToGame(EsrbRatingsId, gameId);
        return ResponseEntity.status(HttpStatus.CREATED).body(addRatings);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<EsrbRating>> findAllEsrbRatings() {
        List<EsrbRating> esrbRatings = esrbRatingsService.findAllEsrbRatings();
        return ResponseEntity.ok(esrbRatings);
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<EsrbRating>> findEsrbRatingByGameId(@PathVariable Integer gameId) {
        List<EsrbRating> esrbRatings = esrbRatingsService.findEsrbRatingbyGameId(gameId);
        return ResponseEntity.ok(esrbRatings);
    }
}
