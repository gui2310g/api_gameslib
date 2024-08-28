package com.gui.api_gameslib.Controllers;

import com.gui.api_gameslib.Services.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GamesController {

    @Autowired
    private GamesService gamesService;


}
