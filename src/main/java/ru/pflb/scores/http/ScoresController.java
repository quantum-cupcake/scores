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
    public String getHiScores(@PathVariable int levelId) {
        return scoreService.highScores(levelId);
    }

    @PostMapping(path = "/score/{levelId}")
    public String setHiScores(@PathVariable int levelId, @RequestParam("sessionKey") String sessionKey, @RequestBody String score) {
        System.out.println("/score/{levelId}");
        return scoreService.record(sessionKey, Integer.parseInt(score), levelId);
    }

    @PostMapping(path = "/score/{levelId}/{userId}")
    public String setHiScores(@PathVariable int levelId, @PathVariable int userId, @RequestParam("sessionKey") String sessionKey, @RequestBody String score) {
        System.out.println("/score/{levelId}/{userId}");
        return scoreService.recordAnotherUser(sessionKey, Integer.parseInt(score), levelId, userId);
    }
}
