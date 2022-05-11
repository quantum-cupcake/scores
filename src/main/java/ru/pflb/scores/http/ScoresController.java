package ru.pflb.scores.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.pflb.scores.service.LoginService;
import ru.pflb.scores.service.ScoreService;

@RestController
public class ScoresController {

    private final ScoreService scoreServices;

    @Autowired
    public ScoresController(ScoreService scoreServices) {
        this.scoreServices = scoreServices;
    }

    @GetMapping(path = "/hiscores/{levelId}")
    //TODO implement
    public String getHighScores(@PathVariable int levelId) {
        return scoreServices.highScores(levelId);
    }

    @PostMapping(path = "/score/{levelId}/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    //TODO implement; pass sessionKey as Query Param (/.../../..?session={sessionKey})
    public void record(@PathVariable int levelId, @PathVariable int userId, @RequestParam String sessionKey, @RequestBody int score) {

        scoreServices.record(sessionKey,score,levelId);
    }
}
