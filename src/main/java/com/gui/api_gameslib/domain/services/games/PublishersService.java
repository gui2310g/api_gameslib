package com.gui.api_gameslib.domain.services.games;

import com.gui.api_gameslib.domain.entities.Games;
import com.gui.api_gameslib.domain.entities.Publishers;
import com.gui.api_gameslib.domain.Repositories.games.GamesRepository;
import com.gui.api_gameslib.domain.Repositories.games.PublishersRepository;
import com.gui.api_gameslib.domain.exceptions.GamesException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PublishersService {
    private final PublishersRepository publishersRepository;

    private final GamesRepository gamesRepository;

    public Publishers addPublishers(Publishers publishers)  {
        if(publishersRepository.findByName(publishers.getName()).isPresent())
            throw new GamesException("This publisher is already added");

        return publishersRepository.save(publishers);
    }

    public List<Publishers> findAllPublishers() {
        return publishersRepository.findAll().stream().toList();
    }

    public Publishers addPublishersToGame(Integer publishersid, Integer gameId) {
        Games games = gamesRepository.findById(gameId)
                .orElseThrow(() -> new GamesException("Could not find a game with this id: " + gameId));

        Publishers publishers = publishersRepository.findById(publishersid)
                .orElseThrow(() -> new GamesException("Could not find a publisher"));

        if (games.getPublishers().stream().anyMatch(p -> p.getId().equals(publishersid)))
            throw new Error("This publisher is already added in this game");

        games.getPublishers().add(publishers);
        gamesRepository.save(games);

        return publishers;
    }

    public List<Publishers> findAllPublishersByGameid(Integer gameId) {
        Games game = gamesRepository.findById(gameId)
               .orElseThrow(() -> new GamesException("Game not found with ID: " + gameId));

        return game.getPublishers().stream().toList();
    }
}
