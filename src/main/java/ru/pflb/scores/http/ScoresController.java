package ru.pflb.scores.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.pflb.scores.service.LoginService;
import ru.pflb.scores.service.ScoreService;

@RestController
public class ScoresController {


    private final ScoreService scoreService;

    //private final LoginService loginService;

    @Autowired
    public ScoresController(ScoreService scoreService, LoginService loginService) {
        this.scoreService = scoreService;
        //this.loginService = loginService;
    }
    @GetMapping(path = "/hiscores/{levelId}")
    public String getHighScores(@PathVariable int levelId) {
        return scoreService.highScores(levelId);
    }

    @PostMapping(path = "/score/{levelId}/{userId}?sessionKey={sessionKey}")
    public String recordLevel(@PathVariable int userId, int levelId, @RequestParam String sessionKey, @RequestBody int score) {
        scoreService.record(sessionKey, score, levelId);
        return scoreService.highScores(levelId);
    }
    //TODO implement; pass sessionKey as Query Param (/.../../..?session={sessionKey})
}
