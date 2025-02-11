package org.portfolio.competitormanager.model;

import java.util.List;
import java.util.Arrays;

/**
 * Represents the users quiz results
 */
public class Result {
    private int result_id;
    private int user_id;
    private String username;
    private String score; // CSV of scores
    private double averageScore;

    /**
     * constructs a result object
     * @param result_id unique result id
     * @param user_id users userid
     * @param username username for the user
     * @param score score of the quiz
     * @param averageScore calculated average scores
     */
    public Result(int result_id, int user_id, String username, String score, Double averageScore) {
        this.result_id = result_id;
        this.user_id = user_id;
        this.username = username;
        this.score = score;
        this.averageScore = calculateAverageScore(score);
    }

    public double calculateAverageScore(String scores) {
        List<String> scoreList = Arrays.asList(scores.split(","));
        double sum = 0;
        for (String s : scoreList) {
            sum += Integer.parseInt(s.trim());
        }
        return sum / scoreList.size();
    }

    // Getters and setters methods

    public int getResult_id() {
        return result_id;
    }

    public void setResult_id(int result_id) {
        this.result_id = result_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }
}
