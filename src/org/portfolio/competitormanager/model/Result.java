package org.portfolio.competitormanager.model;

public class Result {
    private int result_id;
    private int user_id;
    private String username;
    private int score;
    private String difficulty;

    public Result(int result_id, int user_id, String username, int score, String difficulty) {
        this.result_id = result_id;
        this.user_id = user_id;
        this.username = username;
        this.score = score;
        this.difficulty = difficulty;
    }

    public int getResultId() {
        return result_id;
    }

    public void setResultId(int resultId) {
        this.result_id = resultId;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int userId) {
        this.user_id = userId;
    }
}
