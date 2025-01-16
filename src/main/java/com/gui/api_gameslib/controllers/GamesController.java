package com.gui.api_gameslib.controllers;

import com.gui.api_gameslib.Services.AuthenticationService;
import com.gui.api_gameslib.dto.GameRequest;
import com.gui.api_gameslib.entities.Games;
import com.gui.api_gameslib.Services.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/games")
public class GamesController {

    @Autowired
    private GamesService gamesService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<GameRequest> AddGames(@RequestBody GameRequest gameRequest) {
        GameRequest addedGame = gamesService.AddGames(gameRequest);
        return ResponseEntity.ok(addedGame);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Games>> findAllGames() {
        List<Games> games = gamesService.findAllGames();
        return ResponseEntity.ok(games);
    }

    @GetMapping("/findAllByPage")
    public ResponseEntity<Page<Games>> FindGamesByPagination(@RequestParam int page, @RequestParam int size) {
        Page<Games> games = gamesService.FindGamesByPagination(page, size);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Games> FindGamesById(@PathVariable Integer id) {
        Games game = gamesService.FindGamesById(id);
        return ResponseEntity.ok(game);
    }

    @GetMapping("/findByAuth")
    public ResponseEntity<Set<Games>> findWishlistGamesByUser(Authentication authentication) {
        Integer userId = authenticationService.getAuthenticatedUserId(authentication);
        Set<Games> wishlistGames = gamesService.findWishlistGamesByUser(userId);
        return ResponseEntity.ok(wishlistGames);
    }

    @GetMapping("/find/publishers/{publishersId}")
    public ResponseEntity<List<Games>> findGamesByPublishersId(@PathVariable Integer publishersId) {
        List<Games> games = gamesService.findGamesByPublishersId(publishersId);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/find/platforms/{platformsId}")
    public ResponseEntity<List<Games>> findGamesByPlatformId(@PathVariable Integer platformsId) {
        List<Games> games = gamesService.findGamesByPlatformsId(platformsId);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Games>> SearchGames(@RequestParam String name) {
        List<Games> games = gamesService.SearchGames(name);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Games>> filterGames(
            @RequestParam(required = false) Integer genresId,
            @RequestParam(required = false) Integer platformsId,
            @RequestParam(required = false) Integer publishersId) {
        List<Games> games = gamesService.filterGames(genresId, platformsId, publishersId);
        return ResponseEntity.ok(games);
    }
}
