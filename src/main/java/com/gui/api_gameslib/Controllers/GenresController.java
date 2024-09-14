package com.gui.api_gameslib.Controllers;

import com.gui.api_gameslib.Models.Genres;
import com.gui.api_gameslib.Services.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genres")
public class GenresController {

    @Autowired
    private GenresService genresService;

    @PostMapping("/add")
    public ResponseEntity<Genres> addGenres(@RequestBody Genres genres) {
        Genres addedGenre = genresService.addGenres(genres);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedGenre);
    }
}
