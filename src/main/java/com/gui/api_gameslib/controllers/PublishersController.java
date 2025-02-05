package com.gui.api_gameslib.controllers;

import com.gui.api_gameslib.entities.Publishers;
import com.gui.api_gameslib.Services.PublishersService;
import org.springframework.data.domain.Page;
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
        Publishers addedPublisher = publishersService.addPublishers(publishers);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPublisher);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Publishers>> findAllPublishers() {
        List<Publishers> publishers = publishersService.findAllPublishers();
        return ResponseEntity.ok(publishers);
    }
    @GetMapping("/findAllByPage")
    public ResponseEntity<Page<Publishers>> findPublishersByPagination(@RequestParam int page, @RequestParam int size) {
        Page<Publishers> publishers = publishersService.findPublishersByPagination(page, size);
        return ResponseEntity.ok(publishers);
    }

    @PostMapping("/add/{gameId}/{publishersId}")
    public ResponseEntity<Publishers> addPublishersToGame(
            @PathVariable Integer publishersId,
            @PathVariable Integer gameId
    ) {
        Publishers addedPublishersToGame = publishersService.addPublishersToGame(publishersId, gameId);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPublishersToGame);
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<Publishers>> findAllPublishersByGameid(@PathVariable Integer gameId) {
        List<Publishers> publishers = publishersService.findAllPublishersByGameid(gameId);
        return ResponseEntity.ok(publishers);
    }
}
