package com.gui.api_gameslib.controller.games;

import com.gui.api_gameslib.domain.entities.Screenshots;
import com.gui.api_gameslib.domain.services.games.ScreenshotsService;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(screenshotsService.addScreenshotToGame(screenshots));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Screenshots>> findAllScreenshots() {
        return ResponseEntity.ok(screenshotsService.findAllScreenshots());
    }

    @PostMapping("/add/{gameId}/{screenshotId}")
    public ResponseEntity<Screenshots> addScreenshotsToGame(
            @PathVariable Integer screenshotId,
            @PathVariable Integer gameId
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(screenshotsService.addScreenshotsToGame(screenshotId, gameId));
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<Screenshots>> findAllScreenshotsByGameId(@PathVariable Integer gameId) {
        return ResponseEntity.ok(screenshotsService.findAllScreenshotsByGameId(gameId));
    }
}
