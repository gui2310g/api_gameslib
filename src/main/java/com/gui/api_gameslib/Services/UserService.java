package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.Models.Users;
import com.gui.api_gameslib.Repositories.UsersRepository;
import com.gui.api_gameslib.UserAuthenticated;
import com.gui.api_gameslib.exceptions.UserException;

import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UsersRepository usersRepository;

    public Users CreateUser(Users users) throws UserException {
        if (usersRepository.findByUsername(users.getEmail()).isPresent())
            throw new UserException("Error: User already exists");

        return usersRepository.save(users);
    };

    public Iterable<Users> FindAllUsers() throws UserException {
        Iterable<Users> users = usersRepository.findAll();
        if(!users.iterator().hasNext()) throw new UserException("There is no registered Users");
        return usersRepository.findAll();
    }

    public Users FindUserById(Integer id) throws UserException {
        return usersRepository.findById(id).orElseThrow(
                () -> new UserException("Can't found the user with this id")
        );
    }

    public Users updateUser(Users users, Integer id) throws UserException {
        Users existingUser = usersRepository.findById(id)
                .orElseThrow(() -> new UserException("Can't found the user with this id to update"));

        if (users.getUsername() != null) users.setUsername(users.getUsername());

        if (users.getPassword() != null) users.setPassword(users.getPassword());

        return usersRepository.save(existingUser);
    }

    public Users DeleteUser(Integer id) throws UserException {
        Users existingUser = usersRepository.findById(id)
                .orElseThrow(() -> new UserException("Can't find the user with this ID to delete"));

        usersRepository.delete(existingUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository
                .findByUsername(username)
                .map(UserAuthenticated::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
