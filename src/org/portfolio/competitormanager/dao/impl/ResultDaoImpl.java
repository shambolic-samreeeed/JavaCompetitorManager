package org.portfolio.competitormanager.dao.impl;

import org.portfolio.competitormanager.connection.ConnectionToDB;
import org.portfolio.competitormanager.dao.ResultDao;
import org.portfolio.competitormanager.model.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ResultDaoImpl implements ResultDao {

    /**
     * saves the result of the user to the database
     * @param result the result object containing the result information of the user
     */
    @Override
    public int save(Result result) throws SQLException, ClassNotFoundException {
        String selectQuery = "SELECT COUNT(*) FROM results WHERE user_id = ?";
        String updateQuery = "UPDATE results SET score = ?, average_score = ? WHERE user_id = ?";
        String insertQuery = "INSERT INTO results (user_id, username, score, average_score) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {

            // Step 1: Check if a record exists for this user
            selectStatement.setInt(1, result.getUser_id());
            ResultSet resultSet = selectStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1); // Get the count of existing records

            if (count > 0) {
                // Step 2: If a record exists, replace the existing score
                try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                    updateStatement.setString(1, result.getScore()); // Replace with new score
                    updateStatement.setDouble(2, result.getAverageScore());
                    updateStatement.setInt(3, result.getUser_id());
                    return updateStatement.executeUpdate();
                }
            } else {
                // Step 3: If no record exists, insert a new one
                try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                    insertStatement.setInt(1, result.getUser_id());
                    insertStatement.setString(2, result.getUsername());
                    insertStatement.setString(3, result.getScore());
                    insertStatement.setDouble(4, result.getAverageScore());
                    return insertStatement.executeUpdate();
                }
            }
        }
    }


    /**
     * retrieves the user data using the users id
     * @param user_id the id of the user whose data is to be retrieved
     * @return results of the user
     */
    @Override
    public List<Result> findByUserId(int user_id) throws SQLException, ClassNotFoundException {
        String selectQuery = "SELECT * FROM results WHERE user_id=?";
        List<Result> results = new ArrayList<>();
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                results.add(new Result(
                        resultSet.getInt("result_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("score"),
                        resultSet.getDouble("average_score")
                ));
            }
        }
        return results;
    }

    /**
     * retrieves all the data from the database
     * @return results object containing all the results data
     */
    @Override
    public List<Result> findAll() throws SQLException, ClassNotFoundException {
        String selectQuery = "SELECT * FROM results";
        List<Result> results = new ArrayList<>();
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                results.add(new Result(
                        resultSet.getInt("result_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("score"),
                        resultSet.getDouble("average_score")
                ));
            }
        }
        return results;
    }

    /**
     * Retrieves all the users scores and displays them in descending order
     * @return leaderboard object that contains all the users results data
     */
    @Override
    public List<String> getLeaderboard() throws SQLException, ClassNotFoundException {
        String selectQuery = "SELECT username, average_score FROM results ORDER BY average_score DESC LIMIT 10";
        List<String> leaderboard = new ArrayList<>();

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                double score = resultSet.getDouble("score");
                leaderboard.add(username + " - " + score);
            }
        }

        return leaderboard;
    }

//    @Override
//    public int update(Result result) throws SQLException, ClassNotFoundException {
//        // Step 1: Retrieve the existing result first
//        String selectQuery = "SELECT score FROM results WHERE user_id=?";
//        String currentScores = "";
//
//        try (Connection connection = ConnectionToDB.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
//            preparedStatement.setInt(1, result.getUser_id());
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                currentScores = resultSet.getString("score");
//            } else {
//                System.out.println("No user found with user_id: " + result.getUser_id());
//                return 0; // No matching user found
//            }
//        }
//
//        // Step 2: Append new score to the CSV values (handle empty input)
//        String newScore = result.getScore().trim();
//        String updatedScores = currentScores;
//        if (!newScore.isEmpty()) {
//            updatedScores += (currentScores.isEmpty() ? "" : ",") + newScore;
//        }
//
//        // Step 3: Recalculate the average score (you might want to validate the scores first)
//        double updatedAverage = result.calculateAverageScore(updatedScores);
//
//        // Debugging: Log the updated values
//        System.out.println("Updated scores: " + updatedScores);
//        System.out.println("Updated average score: " + updatedAverage);
//
//        // Step 4: Prepare the update query to save new score and average
//        String updateQuery = "UPDATE results SET score = ?, average_score = ? WHERE user_id = ?";
//        try (Connection connection = ConnectionToDB.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
//
//            // Step 5: Set parameters
//            preparedStatement.setString(1, updatedScores);
//            preparedStatement.setDouble(2, updatedAverage);
//            preparedStatement.setInt(3, result.getUser_id());
//
//            // Step 6: Execute the update and check rows affected
//            int rowsUpdated = preparedStatement.executeUpdate();
//            if (rowsUpdated > 0) {
//                System.out.println("Record updated successfully.");
//            } else {
//                System.out.println("No rows were updated.");
//            }
//            return rowsUpdated;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Error executing update: " + e.getMessage());
//            return 0;
//        }
//    }
//}

    /**
     * Updates the result of the user when they play the quiz for more than one time.
     * @param result the result object that contains the result that is to be stored.
     * @return the updated rows
     */
    @Override
    public int update(Result result) throws SQLException, ClassNotFoundException {
        String userSelectQuery = "SELECT score FROM results WHERE user_id=?";
        String currentScores = "";

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(userSelectQuery)) {
            preparedStatement.setInt(1, result.getUser_id());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                currentScores = resultSet.getString("score");
            }
        }

        String newScore = result.getScore().trim();
        String updatedScores = currentScores;
        if (!newScore.isEmpty()) {
            if (updatedScores.isEmpty()) {
                updatedScores = newScore;
            } else {
                updatedScores += "," + newScore;
            }
        }

        double updatedAverage = result.calculateAverageScore(updatedScores);

        String updateQuery = "UPDATE results SET score = ?, average_score = ? WHERE user_id = ?";
        int rowsUpdated = 0;

        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, updatedScores);
            preparedStatement.setDouble(2, updatedAverage);
            preparedStatement.setInt(3, result.getUser_id());

            rowsUpdated = preparedStatement.executeUpdate();
        }

        if (rowsUpdated == 0) {
            String insertQuery = "INSERT INTO results (user_id, score, average_score) VALUES (?, ?, ?)";
            try (Connection connection = ConnectionToDB.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, result.getUser_id());
                preparedStatement.setString(2, updatedScores);
                preparedStatement.setDouble(3, updatedAverage);
                preparedStatement.executeUpdate();
            }
        }

        return rowsUpdated;
    }

}

