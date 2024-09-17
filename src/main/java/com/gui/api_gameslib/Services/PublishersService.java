package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.Repositories.PublishersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PublishersService {

    private final PublishersRepository publishersRepository;
}
