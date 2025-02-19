package com.gui.api_gameslib.controller.games;

import com.gui.api_gameslib.domain.entities.Genres;
import com.gui.api_gameslib.domain.services.games.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenresController {

    @Autowired
    private GenresService genresService;

    @PostMapping
    public ResponseEntity<Genres> addGenres(@RequestBody Genres genres) {
        return ResponseEntity.status(HttpStatus.CREATED).body(genresService.addGenres(genres));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Genres>> findAllGenres() {
        return ResponseEntity.ok(genresService.findAllGenres());
    }

    @PostMapping("/add/{gameId}/{genresId}")
    public ResponseEntity<Genres> addGenresToGame(@PathVariable Integer genresId, @PathVariable Integer gameId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(genresService.addGenresToGame(genresId, gameId));
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<Genres>> findAllGenresByGameId(@PathVariable Integer gameId) {
        return ResponseEntity.ok(genresService.findAllGenresByGameId(gameId));
    }
}