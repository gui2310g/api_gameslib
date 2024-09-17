package com.gui.api_gameslib.Controllers;

import com.gui.api_gameslib.Services.PublishersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publishers")
public class PublishersController {
    @Autowired
    private PublishersService publishersService;
}
