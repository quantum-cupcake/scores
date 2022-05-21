package ru.pflb.scores.service;

public interface ScoreService {

    String record(String sessionKey, int score, int levelId) throws IllegalStateException;

    String highScores(int levelId);

    String recordAnotherUser(String sessionKey, int parseInt, int levelId, int userId);
}
