package com.gui.api_gameslib.controller.games;

import com.gui.api_gameslib.domain.entities.Publishers;
import com.gui.api_gameslib.domain.services.games.PublishersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublishersController {

    @Autowired
    private PublishersService publishersService;

    @PostMapping
    public ResponseEntity<Publishers> addPublishers(@RequestBody Publishers publishers) {
        return ResponseEntity.status(HttpStatus.CREATED).body(publishersService.addPublishers(publishers));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Publishers>> findAllPublishers() {
        return ResponseEntity.ok(publishersService.findAllPublishers());
    }

    @PostMapping("/add/{gameId}/{publishersId}")
    public ResponseEntity<Publishers> addPublishersToGame(
            @PathVariable Integer publishersId,
            @PathVariable Integer gameId
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(publishersService.addPublishersToGame(publishersId, gameId));
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<Publishers>> findAllPublishersByGameid(@PathVariable Integer gameId) {
        return ResponseEntity.ok(publishersService.findAllPublishersByGameid(gameId));
    }
}
