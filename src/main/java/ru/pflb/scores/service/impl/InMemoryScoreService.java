package ru.pflb.scores.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pflb.scores.dto.HighScores;
import ru.pflb.scores.dto.LevelHighScores;
import ru.pflb.scores.dto.User;
import ru.pflb.scores.repository.UserRepository;
import ru.pflb.scores.service.LoginService;
import ru.pflb.scores.service.ScoreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryScoreService implements ScoreService {

    private final LoginService loginService;
    private final Map<Integer, LevelHighScores> scoreboard = new ConcurrentHashMap<>();

    public InMemoryScoreService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Autowired
    UserRepository userRepository;

    @Override
    public String record(String sessionKey, int score, int levelId) {
        var userId = loginService.getUserId(sessionKey);
        recordScore(userId, levelId, score);
        return sessionKey;
    }

    @Override
    public String recordAnotherUser(String sessionKey, int score, int levelId, int userId) {
        var currentUserId = loginService.getUserId(sessionKey);
        if (currentUserId > 0) {
            recordScore(userId, levelId, score);
        }
        return sessionKey;
    }

    private void recordScore(int userId, int levelId, int score) {
        /*scoreboard.compute(levelId, (levelIdKey, levelHighScores) -> {
            levelHighScores = levelHighScores == null ? new LevelHighScores() : levelHighScores;
            levelHighScores.recordScore(userId, score);
            return levelHighScores;
        });*/

        User userFromDB = userRepository.findByUserIdAndLevel(userId, levelId);
        if (userFromDB != null) {
            userFromDB.setScore(score);
            userRepository.save(userFromDB);
        }
        else {
            User user = new User(userId, levelId, score);
            userRepository.save(user);
        }
    }

    @Override
    public String highScores(int levelId) {
        /*return Optional.ofNullable(scoreboard.get(levelId))
                .map(LevelHighScores::getHighScores)
                .map(HighScores::toString).orElse("no data");*/

        List<User> list = new ArrayList<>();
        userRepository.findByLevel(levelId).forEach(e -> list.add(e));
        String str = "";
        for (User s : list) {
            str+= s+",";
        }
        return str;
    }
}
