package com.gui.api_gameslib.Controllers;

import com.gui.api_gameslib.Models.EsrbRating;
import com.gui.api_gameslib.Services.EsrbRatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class EsrbRatingsController {

    @Autowired
    private EsrbRatingsService esrbRatingsService;

    @PostMapping("/add")
    public ResponseEntity<EsrbRating> AddEsrbRatings(@RequestBody EsrbRating esrbRating) {
        EsrbRating createdRating = esrbRatingsService.AddEsrbRatings(esrbRating);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRating);
    }

    @PostMapping("/add/{gameId}")
    public ResponseEntity<EsrbRating> AddEsrbRatingsToGame(@RequestBody EsrbRating esrbRating, @PathVariable Integer gameId) {
        String esrbRatingName = esrbRating.getName();
        EsrbRating addRatings = esrbRatingsService.addEsrbRatingsToGame(esrbRatingName, gameId);
        return ResponseEntity.status(HttpStatus.CREATED).body(addRatings);
    }

    @GetMapping("/find")
    public ResponseEntity<Iterable<EsrbRating>> findAllEsrbRatings() {
        Iterable<EsrbRating> esrbRatings = esrbRatingsService.findAllEsrbRatings();
        return ResponseEntity.ok(esrbRatings);
    }
}
