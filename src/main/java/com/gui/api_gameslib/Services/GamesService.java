package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.Repositories.*;
import com.gui.api_gameslib.dto.Game.GameRequest;
import com.gui.api_gameslib.dto.Game.GameResponse;
import com.gui.api_gameslib.entities.*;
import com.gui.api_gameslib.exceptions.GamesException;
import com.gui.api_gameslib.exceptions.UserException;
import com.gui.api_gameslib.mappers.GameMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GamesService {
    private final GamesRepository gamesRepository;

    private final GameMapper gameMapper;

    private final UsersRepository usersRepository;

    public GameResponse AddGames(GameRequest gameRequest) {
        if (gamesRepository.findByName(gameRequest.getName()).isPresent())
            throw new GamesException("This game already exists");

        Games games = gameMapper.toEntity(gameRequest);

        return gameMapper.toDto(gamesRepository.save(games));
    }

    public List<GameResponse> findAllGames() {
        return gamesRepository.findAll().stream().map(this::toDto).toList();
    }

    public Page<GameResponse> FindGamesByPagination(int page, int size) {
        Page<Games> games = gamesRepository.findAll(PageRequest.of(page, size));

        if (page >= games.getTotalPages()) throw new GamesException("Page number out of bounds");

        List<GameResponse> gameResponses = games.getContent().stream().map(this::toDto).toList();

        return new PageImpl<>(gameResponses, PageRequest.of(page, size), games.getTotalElements());
    }

    public GameResponse FindGamesById(Integer id) {
        return gamesRepository.findById(id).map(this::toDto).orElseThrow(() -> new GamesException("No game id found"));
    }

    public List<GameResponse> SearchGames(String name) {
        return gamesRepository.findByNameContainingIgnoreCase(name).stream().map(this::toDto).toList();
    }

    public List<GameResponse> findWishlistGamesByUser(Integer userId) {
        Users users = usersRepository.findById(userId).orElseThrow(() -> new UserException("No user fund"));
        return users.getWishlistGames().stream().map(this::toDto).toList();
    }

    public GameResponse toDto(Games games) { return gameMapper.toDto(games); }
}
