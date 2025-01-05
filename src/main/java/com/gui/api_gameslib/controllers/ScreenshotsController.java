package com.gui.api_gameslib.controllers;

import com.gui.api_gameslib.entities.Screenshots;
import com.gui.api_gameslib.Services.ScreenshotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/screenshots")
public class ScreenshotsController {

    @Autowired
    private ScreenshotsService screenshotsService;

    @PostMapping
    public ResponseEntity<Screenshots> addScreenshotToGame(@RequestBody Screenshots screenshots) {
        Screenshots addedScreenshot = screenshotsService.addScreenshotToGame(screenshots);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedScreenshot);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Screenshots>> findAllScreenshots() {
        List<Screenshots> screenshots = screenshotsService.findAllScreenshots();
        return ResponseEntity.ok(screenshots);
    }

    @PostMapping("/add/{gameId}/{screenshotId}")
    public ResponseEntity<Screenshots> addScreenshotsToGame(
            @PathVariable Integer screenshotId,
            @PathVariable Integer gameId
    ) {
        Screenshots addedScreenshot = screenshotsService.addScreenshotsToGame(screenshotId, gameId);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedScreenshot);
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<Screenshots>> findAllScreenshotsByGameId(@PathVariable Integer gameId) {
        List<Screenshots> screenshots = screenshotsService.findAllScreenshotsByGameId(gameId);
        return ResponseEntity.ok(screenshots);
    }
}
