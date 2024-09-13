package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.Repositories.GenresRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenresService {
    private final GenresRepository genresRepository;


}
