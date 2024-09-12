package com.gui.api_gameslib.Controllers;

import com.gui.api_gameslib.Models.Platforms;
import com.gui.api_gameslib.Services.PlatformsService;
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

    @PostMapping("/add")
    public ResponseEntity<Platforms> AddPlatforms(@RequestBody Platforms platforms) {
        Platforms addedPlatforms = platformsService.addPlatform(platforms);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPlatforms);
    }

    @PostMapping("/add/{gameId}/{platformsId}")
    public ResponseEntity<Platforms> addPlatformToGame(@PathVariable Integer platformsId, @PathVariable Integer gameId) {
        Platforms addedPlatformsToGame = platformsService.addPlatformToGame(platformsId, gameId);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPlatformsToGame);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Platforms>> findAllPlatforms() {
        List<Platforms> platforms = platformsService.findAllPlatforms();
        return ResponseEntity.ok(platforms);
    }
}
