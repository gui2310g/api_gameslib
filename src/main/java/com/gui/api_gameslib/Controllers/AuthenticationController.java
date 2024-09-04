package com.gui.api_gameslib.Controllers;

import com.gui.api_gameslib.Services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor

@RestController
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @GetMapping("/")
    public String authenticate() {
        return "";
    }
}
