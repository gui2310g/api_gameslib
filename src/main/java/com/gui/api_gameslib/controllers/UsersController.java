package com.gui.api_gameslib.controllers;

import com.gui.api_gameslib.Services.AuthenticationService;
import com.gui.api_gameslib.Services.UserService;
import com.gui.api_gameslib.dto.user.UserRequest;
import com.gui.api_gameslib.dto.user.UserResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authService;

    @PostMapping
    public ResponseEntity<UserResponse> CreateUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.CreateUser(userRequest));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserResponse>> FindAllUsers() {
        return ResponseEntity.ok(userService.FindAllUsers());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserResponse> FindUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.FindUserById(id));
    }

    @PutMapping("/update")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest user, Authentication auth) {
        return ResponseEntity.ok(userService.updateUser(user, authService.getAuthenticatedUserId(auth)));
    }

    @DeleteMapping("/delete")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> deleteUser(Authentication auth) {
        userService.DeleteUser(authService.getAuthenticatedUserId(auth));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/wishlist/add/{gameId}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<UserResponse> addGameToUser(@PathVariable Integer gameId, Authentication auth) {
        return ResponseEntity.ok(userService.AddGamestoUser(authService.getAuthenticatedUserId(auth), gameId));
    }

    @DeleteMapping("/wishlist/delete/{gameId}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> RemoveGamefromUser(@PathVariable Integer gameId, Authentication auth) {
        userService.RemoveGamefromUser(authService.getAuthenticatedUserId(auth), gameId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
