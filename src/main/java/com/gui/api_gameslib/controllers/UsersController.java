package com.gui.api_gameslib.controllers;

import com.gui.api_gameslib.Services.UserService;
import com.gui.api_gameslib.entities.Users;
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

    private String getUsername(Authentication authentication) {
        return authentication.getName();
    }

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

    @PutMapping("/update")
    public ResponseEntity<Users> updateUser(@RequestBody Users users, Authentication authentication) {
        Users updatedUser = userService.updateUser(users, getUsername(authentication));
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(Authentication authentication) {
        Users deletedUser = userService.DeleteUser(getUsername(authentication));
        return ResponseEntity.ok("user deleted");
    }

    @PostMapping("/wishlist/add/{gameId}")
    public ResponseEntity<Users> addGameToUser(@PathVariable Integer gameId, Authentication authentication) {
        Users user = userService.AddGamestoUser(getUsername(authentication), gameId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/wishlist/delete/{gameId}")
    public ResponseEntity<Users> RemoveGamefromUser(@PathVariable Integer gameId, Authentication authentication) {
        Users user = userService.RemoveGamefromUser(getUsername(authentication), gameId);
        return ResponseEntity.ok(user);
    }
}
