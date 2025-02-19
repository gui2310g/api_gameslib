package com.gui.api_gameslib.controller.games;

import com.gui.api_gameslib.domain.entities.Platforms;
import com.gui.api_gameslib.domain.services.games.PlatformsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/platforms")
public class PlatformsController {

    @Autowired
    private PlatformsService platformsService;

    @PostMapping
    public ResponseEntity<Platforms> AddPlatforms(@RequestBody Platforms platforms) {
        return ResponseEntity.status(HttpStatus.CREATED).body(platformsService.addPlatform(platforms));
    }

    @PostMapping("/add/{gameId}/{platformsId}")
    public ResponseEntity<Platforms> addPlatformToGame(
            @PathVariable Integer platformsId,
            @PathVariable Integer gameId
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(platformsService.addPlatformToGame(platformsId, gameId));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Platforms>> findAllPlatforms() {
        return ResponseEntity.ok(platformsService.findAllPlatforms());
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<Platforms>> findAllPlatformsByGameId(@PathVariable Integer gameId) {
        return ResponseEntity.ok(platformsService.findAllPlatformsByGameId(gameId));
    }
}
