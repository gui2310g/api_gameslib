package com.gui.api_gameslib.controllers;

import com.gui.api_gameslib.entities.Genres;
import com.gui.api_gameslib.Services.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        Genres addedGenre = genresService.addGenres(genres);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedGenre);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Genres>> findAllGenres() {
        List<Genres> genres = genresService.findAllGenres();
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/findAllByPage")
    public ResponseEntity<Page<Genres>> findGenresByPagination(@RequestParam int page, @RequestParam int size) {
        Page<Genres> genres = genresService.findGenresByPagination(page, size);
        return ResponseEntity.ok(genres);
    }

    @PostMapping("/add/{gameId}/{genresId}")
    public ResponseEntity<Genres> addGenresToGame(@PathVariable Integer genresId, @PathVariable Integer gameId) {
        Genres addedGenresToGame = genresService.addGenresToGame(genresId, gameId);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedGenresToGame);
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<Genres>> findAllGenresByGameId(@PathVariable Integer gameId) {
        List<Genres> genres = genresService.findAllGenresByGameId(gameId);
        return ResponseEntity.ok(genres);
    }
}