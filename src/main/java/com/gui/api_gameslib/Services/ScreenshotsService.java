package com.gui.api_gameslib.Services;

import com.gui.api_gameslib.Models.Games;
import com.gui.api_gameslib.Models.Screenshots;
import com.gui.api_gameslib.Repositories.GamesRepository;
import com.gui.api_gameslib.Repositories.ScreenshotsRepository;
import com.gui.api_gameslib.exceptions.GamesException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScreenshotsService {

    private final GamesRepository gamesRepository;

    private final ScreenshotsRepository screenshotsRepository;

    public Screenshots addScreenshotToGame(Screenshots screenshots) throws GamesException {
        if(screenshotsRepository.findByUrl(screenshots.getUrl()).isPresent())
            throw new GamesException("This screenshot is already added");

        return screenshotsRepository.save(screenshots);
    }

    public Screenshots addScreenshotsToGame(Integer screenshotid, Integer gameId) throws GamesException {
        Games game = gamesRepository.findById(gameId)
                .orElseThrow(() -> new GamesException("Game not found with ID: " + gameId));

        Screenshots screenshots = screenshotsRepository.findById(screenshotid)
                .orElseThrow(() -> new GamesException("Could not find a screenshot"));

        if (game.getScreenshots().stream().anyMatch(p -> p.getId().equals(screenshotid)))
            throw new Error("This screenshot is already added in this game");

        game.getScreenshots().add(screenshots);
        gamesRepository.save(game);

        return screenshots;
    }

    public List<Screenshots> findAllScreenshots() throws GamesException {
        List<Screenshots> screenshots = screenshotsRepository.findAll();

        if(screenshots.isEmpty()) throw new GamesException("There is no registered screenshots");

        return screenshots;
    }
}
