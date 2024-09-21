package com.gui.api_gameslib.controllers;

import com.gui.api_gameslib.entities.Games;
import com.gui.api_gameslib.Services.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GamesController {
    @Autowired
    private GamesService gamesService;

    @PostMapping("/add")
    public ResponseEntity<Games> AddGames(@RequestBody Games games) {
        Games addedGame = gamesService.AddGames(games);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedGame);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Page<Games>> FindAllGames(@RequestParam int page, @RequestParam int size) {
        Page<Games> games = gamesService.FindAllGames(page, size);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Games> FindGamesById(@PathVariable Integer id) {
        Games game = gamesService.FindGamesById(id);
        return ResponseEntity.ok(game);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Games>> SearchGames(@RequestParam String name) {
        List<Games> games = gamesService.SearchGames(name);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/search/platforms/{platformsId}")
    public ResponseEntity<List<Games>> findGamesByPlatformId(@PathVariable Integer platformsId) {
        List<Games> games = gamesService.findGamesByPlatformsId(platformsId);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/search/publishers/{publishersId}")
    public ResponseEntity<List<Games>> findGamesByPublishersId(@PathVariable Integer publishersId) {
        List<Games> games = gamesService.findGamesByPublishersId(publishersId);
        return ResponseEntity.ok(games);
    }
}
