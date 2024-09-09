package com.gui.api_gameslib.Controllers;

import com.gui.api_gameslib.Models.Platforms;
import com.gui.api_gameslib.Services.PlatformsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/platforms")
public class PlatformsController {

    @Autowired
    public PlatformsService platformsService;

    @PostMapping("/add")
    public ResponseEntity<Platforms> AddPlatforms(@RequestBody Platforms platforms) {
        Platforms addedPlatforms = platformsService.addPlatform(platforms);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPlatforms);
    }

    @PostMapping("/add/{gameId}")
    public ResponseEntity<Platforms> addPlatformToGame(@RequestBody Platforms platforms, @PathVariable Integer gameId) {
        String platformName = platforms.getName();
        Platforms addedPlatformsToGame = platformsService.addPlatformToGame(platformName, gameId);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPlatformsToGame);
    }

    @GetMapping("/find")
    public ResponseEntity<Iterable<Platforms>> findAllPlatforms() {
        Iterable<Platforms> platforms = platformsService.findAllPlatforms();
        return ResponseEntity.ok(platforms);
    }
}
