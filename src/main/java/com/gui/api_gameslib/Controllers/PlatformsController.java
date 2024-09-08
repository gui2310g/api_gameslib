package com.gui.api_gameslib.Controllers;

import com.gui.api_gameslib.Models.Platforms;
import com.gui.api_gameslib.Services.PlatformsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/platforms")
public class PlatformsController {

    @Autowired
    public PlatformsService platformsService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Platforms AddPlatforms(@RequestBody Platforms platforms) {
        return platformsService.addPlatform(platforms);
    }

    @PostMapping("/add/{gameId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Platforms addPlatformToGame(@RequestBody Platforms platforms, @PathVariable Integer gameId) {
        String platformName = platforms.getName();

        return platformsService.addPlatformToGame(platformName, gameId);
    }

    @GetMapping("/find")
    public Iterable<Platforms> findAllPlatforms() {
        return platformsService.findAllPlatforms();
    }
}
