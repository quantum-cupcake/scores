package ru.pflb.scores.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.pflb.scores.service.ScoreService;

@RestController
public class ScoresController {

    private final ScoreService scoreService;

    @Autowired
    public ScoresController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping(path = "/hiscores/{levelId}")
    public String getHighScores(@PathVariable int levelId) {
        return scoreService.highScores(levelId);
    }

    @PostMapping(path = "/score/{levelId}/{userId}")
    public void setScores(@PathVariable int levelId, @PathVariable int userId, @RequestParam String sessionKey, @RequestBody int score) {
        scoreService.record(sessionKey, score, levelId);
    }
}
