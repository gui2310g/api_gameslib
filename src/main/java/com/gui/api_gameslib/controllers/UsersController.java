package com.gui.api_gameslib.controllers;

import com.gui.api_gameslib.Services.AuthenticationService;
import com.gui.api_gameslib.Services.UserService;
import com.gui.api_gameslib.dto.UserRequest;
import com.gui.api_gameslib.entities.Users;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
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
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<UserRequest> CreateUser(@RequestBody UserRequest userRequest) {
        UserRequest createdUser = userService.CreateUser(userRequest);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Users>> FindAllUsers() {
        List<Users> users = userService.FindAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Users> FindUserById(@PathVariable Integer id) {
        Users selectedUser = userService.FindUserById(id);
        return ResponseEntity.ok(selectedUser);
    }

    @PutMapping("/update")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<UserRequest> updateUser(@RequestBody UserRequest userRequest, Authentication authentication) {
        Integer userId = authenticationService.getAuthenticatedUserId(authentication);
        UserRequest updatedUser = userService.updateUser(userRequest, userId);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<UserRequest> deleteUser(UserRequest userRequest, Authentication authentication) {
        Integer userId = authenticationService.getAuthenticatedUserId(authentication);
        UserRequest deletedUser = userService.DeleteUser(userRequest, userId);
        return ResponseEntity.ok(deletedUser);
    }

    @PostMapping("/wishlist/add/{gameId}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Users> addGameToUser(@PathVariable Integer gameId, Authentication authentication) {
        Integer userId = authenticationService.getAuthenticatedUserId(authentication);
        Users user = userService.AddGamestoUser(userId, gameId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/wishlist/delete/{gameId}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Users> RemoveGamefromUser(@PathVariable Integer gameId, Authentication authentication) {
        Integer userId = authenticationService.getAuthenticatedUserId(authentication);
        Users user = userService.RemoveGamefromUser(userId, gameId);
        return ResponseEntity.ok(user);
    }
}
