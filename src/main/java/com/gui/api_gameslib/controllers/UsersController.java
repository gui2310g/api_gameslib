package com.gui.api_gameslib.controllers;

import com.gui.api_gameslib.entities.Users;
import com.gui.api_gameslib.Services.UserService;
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

    @PostMapping("/create")
    public ResponseEntity<Users> CreateUser(@RequestBody Users users) {
        Users createdUser = userService.CreateUser(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
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

    @PutMapping("/update/{id}")
    public ResponseEntity<Users> updateUser(@RequestBody Users users, @PathVariable  Integer id) {
        Users updatedUser = userService.updateUser(users, id);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Users> deleteUser(@PathVariable Integer id) {
        Users deletedUser = userService.DeleteUser(id);
        return ResponseEntity.ok(deletedUser);
    }

    @PostMapping("/wishlist/add/{gameId}")
    public ResponseEntity<Users> addGameToUser(
            @PathVariable Integer gameId,
            Authentication authentication
    ) {
        String username = authentication.getName();

        Users user = userService.AddGamestoUser(username, gameId);

        return ResponseEntity.ok(user);
    }


    @DeleteMapping("/wishlist/delete/{gameId}")
    public ResponseEntity<Users> RemoveGamefromUser(
            @PathVariable Integer gameId,
            Authentication authentication
    ) {
        String username = authentication.getName();

        Users user = userService.RemoveGamefromUser(username, gameId);
        return ResponseEntity.ok(user);
    }
}
