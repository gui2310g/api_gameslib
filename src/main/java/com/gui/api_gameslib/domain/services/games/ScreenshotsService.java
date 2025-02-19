package com.gui.api_gameslib.domain.services.games;

import com.gui.api_gameslib.domain.entities.Games;
import com.gui.api_gameslib.domain.entities.Screenshots;
import com.gui.api_gameslib.domain.Repositories.games.GamesRepository;
import com.gui.api_gameslib.domain.Repositories.games.ScreenshotsRepository;
import com.gui.api_gameslib.domain.exceptions.GamesException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScreenshotsService {
    private final GamesRepository gamesRepository;

    private final ScreenshotsRepository screenshotsRepository;

    public Screenshots addScreenshotToGame(Screenshots screenshots)  {
        if(screenshotsRepository.findByUrl(screenshots.getUrl()).isPresent())
            throw new GamesException("This screenshot is already added");

        return screenshotsRepository.save(screenshots);
    }

    public Screenshots addScreenshotsToGame(Integer screenshotid, Integer gameId) {
        Games game = gamesRepository.findById(gameId)
                .orElseThrow(() -> new GamesException("Game not found with ID: " + gameId));

        Screenshots screenshots = screenshotsRepository.findById(screenshotid)
                .orElseThrow(() -> new GamesException("Screenshot not found with id: " + screenshotid));

        if (game.getScreenshots().stream().anyMatch(p -> p.getId().equals(screenshotid)))
            throw new Error("This screenshot is already added in this game");

        game.getScreenshots().add(screenshots);
        gamesRepository.save(game);

        return screenshots;
    }

    public List<Screenshots> findAllScreenshots() {
        return screenshotsRepository.findAll().stream().toList();
    }

    public List<Screenshots> findAllScreenshotsByGameId(Integer gameId) throws GamesException {
        Games game = gamesRepository.findById(gameId)
               .orElseThrow(() -> new GamesException("Game not found with ID: " + gameId));

        return game.getScreenshots().stream().toList();
    }
}
