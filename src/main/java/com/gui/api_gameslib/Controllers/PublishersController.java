package com.gui.api_gameslib.Controllers;

import com.gui.api_gameslib.Models.Publishers;
import com.gui.api_gameslib.Services.PublishersService;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publishers")
public class PublishersController {
    @Autowired
    private PublishersService publishersService;

    @PostMapping("/add")
    public ResponseEntity<Publishers> addPublishers(@RequestBody Publishers publishers) {
        Publishers addedPublisher = publishersService.addPublishers(publishers);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPublisher);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Page<Publishers>> findAllPublishers(@RequestParam int page, @RequestParam int size) {
        Page<Publishers> publishers = publishersService.findAllPublishers(page, size);
        return ResponseEntity.ok(publishers);
    }

    @PostMapping("/add/{gameId}/{publishersId}")
    public ResponseEntity<Publishers> addPublishersToGame(@PathVariable Integer publishersId, @PathVariable Integer gameId) {
        Publishers addedPublishersToGame = publishersService.addPublishersToGame(publishersId, gameId);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPublishersToGame);
    }
}
