package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.entities.Games;
import com.gui.api_gameslib.entities.Publishers;
import com.gui.api_gameslib.Repositories.GamesRepository;
import com.gui.api_gameslib.Repositories.PublishersRepository;
import com.gui.api_gameslib.exceptions.GamesException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PublishersService {
    private final PublishersRepository publishersRepository;

    private final GamesRepository gamesRepository;
    public Publishers addPublishers(Publishers publishers) throws GamesException {
        if(publishersRepository.findByName(publishers.getName()).isPresent())
            throw new GamesException("This publisher is already added");

        return publishersRepository.save(publishers);
    }

    public Page<Publishers> findAllPublishers(int page, int size) throws GamesException {
        Page<Publishers> publishers = publishersRepository.findAll(PageRequest.of(page, size));

        if (page >= publishers.getTotalPages()) throw new GamesException("Page number out of bounds");

        if(publishers.isEmpty()) throw new GamesException("There is no registered publishers");

        return publishers;
    }

    public Publishers addPublishersToGame(Integer publishersid, Integer gameId) throws GamesException {
        Games games = gamesRepository.findById(gameId)
                .orElseThrow(() -> new GamesException("Could not find a game with this id"));

        Publishers publishers = publishersRepository.findById(publishersid)
                .orElseThrow(() -> new GamesException("Could not find a publisher"));

        if (games.getPublishers().stream().anyMatch(p -> p.getId().equals(publishersid)))
            throw new Error("This publisher is already added in this game");

        games.getPublishers().add(publishers);
        gamesRepository.save(games);

        return publishers;
    }
}
