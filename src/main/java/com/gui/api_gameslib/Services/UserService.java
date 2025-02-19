package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.dto.user.UserRequest;
import com.gui.api_gameslib.dto.user.UserResponse;
import com.gui.api_gameslib.entities.Games;
import com.gui.api_gameslib.entities.Users;
import com.gui.api_gameslib.Repositories.GamesRepository;
import com.gui.api_gameslib.Repositories.UsersRepository;
import com.gui.api_gameslib.exceptions.UserException;

import com.gui.api_gameslib.mappers.UserMapper;
import lombok.AllArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private final UsersRepository usersRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final GamesRepository gamesRepository;

    public UserResponse CreateUser(UserRequest userRequest) {
        if (usersRepository.findByUsername(userRequest.getUsername()).isPresent())
            throw new UserException("Someone use this username");

        validateEmail(userRequest, null);

        Users user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        return userMapper.toDto(usersRepository.save(user));
    }

    public List<UserResponse> FindAllUsers() {
        return usersRepository.findAll().stream().map(this::toDto).toList();
    }

    public UserResponse FindUserById(Integer id) {
        return usersRepository.findById(id).map(this::toDto).orElseThrow(() -> new UserException("no user id found"));
    }

    public UserResponse updateUser(UserRequest userRequest, Integer id) {
        Users existingUser = usersRepository.findById(id).orElseThrow(() -> new UserException("User not found"));

        if (passwordEncoder.matches(userRequest.getPassword(), existingUser.getPassword()))
            throw new UserException("You are already using this password");

        validateEmail(userRequest, id);

        existingUser.setUsername(userRequest.getUsername());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        return userMapper.toDto(usersRepository.save(existingUser));
    }


    public void DeleteUser(Integer id) {
        FindUserById(id);
        usersRepository.deleteById(id);
    }

    public UserResponse AddGamestoUser(Integer id, Integer gameId) {
        Games game = gamesRepository.findById(gameId).orElseThrow(() -> new UserException("No game id found"));

        Users user = usersRepository.findById(id).orElseThrow(() -> new UserException("No user id found"));

        if (user.getWishlistGames().contains(game)) throw new UserException("This game has already been added");
        user.getWishlistGames().add(game);

        return userMapper.toDto(usersRepository.save(user));
    }

    public void RemoveGamefromUser(Integer id, Integer gameId) {
        Games game = gamesRepository.findById(gameId).orElseThrow(() -> new UserException("No game id found"));

        Users user = usersRepository.findById(id).orElseThrow(() -> new UserException("No user id found"));

        if (!user.getWishlistGames().contains(game)) throw new UserException("Game is not in wishlist");

        user.getWishlistGames().remove(game);
        usersRepository.save(user);
    }

    public UserResponse toDto(Users users) {
        return userMapper.toDto(users);
    }

    public void validateEmail(UserRequest dto, Integer userId) {
        usersRepository.findByEmail(dto.getEmail()).ifPresent(existingUser -> {
            if (!existingUser.getId().equals(userId)) throw new UserException("This email is already in use");
            if (dto.getEmail().equals(existingUser.getEmail())) throw new UserException("You're using this email");
        });
    }
}
