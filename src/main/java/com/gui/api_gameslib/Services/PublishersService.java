package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.Models.Publishers;
import com.gui.api_gameslib.Repositories.PublishersRepository;
import com.gui.api_gameslib.exceptions.GamesException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PublishersService {
    private final PublishersRepository publishersRepository;

    public Publishers addPublishers(Publishers publishers) throws GamesException {
        if(publishersRepository.findByName(publishers.getName()).isPresent())
            throw new GamesException("This publisher is already added");

        return publishersRepository.save(publishers);
    }
}
