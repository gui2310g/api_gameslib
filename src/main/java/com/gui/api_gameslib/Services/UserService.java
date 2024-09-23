package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.entities.Games;
import com.gui.api_gameslib.entities.Users;
import com.gui.api_gameslib.Repositories.GamesRepository;
import com.gui.api_gameslib.Repositories.UsersRepository;
import com.gui.api_gameslib.exceptions.UserException;

import lombok.AllArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    private final GamesRepository gamesRepository;

    public Users CreateUser(Users users) throws UserException {
        if (usersRepository.findByEmail(users.getEmail()).isPresent())
            throw new UserException("Email already exists");

        if(usersRepository.findByUsername(users.getUsername()).isPresent())
            throw new UserException("Someone use this username");

        users.setPassword(passwordEncoder.encode(users.getPassword()));

        return usersRepository.save(users);
    }

    public List<Users> FindAllUsers() throws UserException {
        List<Users> users = usersRepository.findAll();

        if(users.isEmpty()) throw new UserException("There is no registered Users");

        return users;
    }

    public Users FindUserById(Integer id) throws UserException {
        return usersRepository.findById(id).orElseThrow(
                () -> new UserException("Can't found the user with this id")
        );
    }

    public Users updateUser(Users users, String username) throws UserException {
        Users existingUser = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UserException("Can't find the user with this username to update"));

        if(usersRepository.findByEmail(users.getEmail()).isPresent()
                || usersRepository.findByUsername(users.getUsername()).isPresent()
        ) throw new UserException("Can't update to this email or username because someone is using");

        if (users.getPassword() != null && passwordEncoder.matches(users.getPassword(), existingUser.getPassword()))
            throw new UserException("You are already using this password");

        if (users.getUsername() != null) existingUser.setUsername(users.getUsername());
        if (users.getEmail() != null) existingUser.setEmail(users.getEmail());
        if (users.getPassword() != null)  existingUser.setPassword(passwordEncoder.encode(users.getPassword()));

        return usersRepository.save(existingUser);
    }

    public Users DeleteUser(String username) throws UserException {
        Users existingUser = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UserException("Can't find the user with this username to delete"));

        usersRepository.delete(existingUser);

        return existingUser;
    }

    public Users AddGamestoUser(String username, Integer gameId) throws UserException {
        Games game = gamesRepository.findById(gameId)
                .orElseThrow(() -> new UserException("Can't find the game with this id"));

        Users existingUser = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UserException("Can't find the user with this username to add the game"));

        if (existingUser.getWishlistGames().stream().anyMatch(p -> p.getId().equals(gameId)))
            throw new UserException("This game is already added in this user");

        existingUser.getWishlistGames().add(game);
        usersRepository.save(existingUser);

        return existingUser;
    }

    public Users RemoveGamefromUser(String username, Integer gameId) throws UserException {
        Games game = gamesRepository.findById(gameId)
               .orElseThrow(() -> new UserException("Can't find the game with this id"));

        Users existingUser = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UserException("Can't find the user with this username to remove the game"));

        if (!existingUser.getWishlistGames().contains(game))
            throw new UserException("This game is not added in this user");

        existingUser.getWishlistGames().remove(game);
        usersRepository.save(existingUser);

        return existingUser;
    }
}
