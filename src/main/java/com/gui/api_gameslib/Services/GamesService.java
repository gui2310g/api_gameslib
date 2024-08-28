package com.gui.api_gameslib.Services;
import com.gui.api_gameslib.Repositories.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamesService {

    @Autowired
    private GamesRepository gamesRepository;


}
