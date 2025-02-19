package com.gui.api_gameslib.controller.games;

import com.gui.api_gameslib.security.AuthenticationService;
import com.gui.api_gameslib.dto.Game.GameRequest;
import com.gui.api_gameslib.dto.Game.GameResponse;
import com.gui.api_gameslib.domain.services.games.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GamesController {

    @Autowired
    private GamesService gamesService;

    @Autowired
    private AuthenticationService authService;

    @PostMapping
    public ResponseEntity<GameResponse> AddGames(@RequestBody GameRequest gameRequest) {
        return ResponseEntity.ok(gamesService.AddGames(gameRequest));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<GameResponse>> findAllGames() {
        return ResponseEntity.ok(gamesService.findAllGames());
    }

    @GetMapping("/findAllByPage")
    public ResponseEntity<Page<GameResponse>> FindGamesByPagination(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(gamesService.FindGamesByPagination(page, size));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<GameResponse> FindGamesById(@PathVariable Integer id) {
        return ResponseEntity.ok(gamesService.FindGamesById(id));
    }

    @GetMapping("/findByAuth")
    public ResponseEntity<List<GameResponse>> findWishlistGamesByUser(Authentication auth) {
        return ResponseEntity.ok(gamesService.findWishlistGamesByUser(authService.getAuthenticatedUserId(auth)));
    }

    @GetMapping("/search")
    public ResponseEntity<List<GameResponse>> SearchGames(@RequestParam String name) {
        return ResponseEntity.ok(gamesService.SearchGames(name));
    }
}
