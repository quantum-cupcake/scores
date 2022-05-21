package ru.pflb.scores.dto;

import javax.persistence.*;

@Entity
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="USER_ID")
    private int userId;

    @Column(name="LEVEL")
    private int level;

    @Column(name="SCORE")
    private int score;

    public User(){
    }

    public User(int userId, int level, int score){
        this.userId = userId;
        this.level = level;
        this.score = score;
    }

    public long getId() {
        return id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ": userId=" + userId + ", level=" + level + ", score=" + score + "]";
    }
}
