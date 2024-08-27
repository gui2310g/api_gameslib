package com.gui.api_gameslib.Controllers;

import com.gui.api_gameslib.Models.Users;
import com.gui.api_gameslib.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Users CreateUser(@RequestBody Users users) {
        return userService.CreateUser(users);
    }

    @GetMapping("/findAll")
    public Iterable<Users> FindAllUsers() {
        return userService.FindAllUsers();
    }

    @GetMapping("/find/{id}")
    public Users FindUserById(@PathVariable Integer id) {
        return userService.FindUserById(id);
    }

    @PutMapping("/update/{id}")
    public Users updateUser(@RequestBody Users users, @PathVariable  Integer id) {
        return userService.updateUser(users, id);
    }

    @DeleteMapping("/delete/{id}")
    public Users deleteUser(@PathVariable Integer id) {
        return userService.DeleteUser(id);
    }
}
