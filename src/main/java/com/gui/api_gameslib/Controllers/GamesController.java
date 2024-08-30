package com.gui.api_gameslib.Controllers;

import com.gui.api_gameslib.Models.Games;
import com.gui.api_gameslib.Services.GamesService;
import org.hibernate.Internal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GamesController {

    @Autowired
    private GamesService gamesService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Games AddGames(@RequestBody Games games) {
        return gamesService.AddGames(games);
    }

    @GetMapping("/findAll")
    public Iterable<Games> FindAllGames() {
        return gamesService.FindAllGames();
    }

    @GetMapping("/find/{id}")
    public Games FindGamesById(@PathVariable Integer id) {
        return gamesService.FindGamesById(id);
    }

    @GetMapping("/search")
    public List<Games> SearchGames(@RequestParam String name) {
        return gamesService.SearchGames(name);
    }
}
