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

}

