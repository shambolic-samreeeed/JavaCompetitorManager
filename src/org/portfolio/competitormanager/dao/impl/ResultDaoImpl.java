package org.portfolio.competitormanager.dao.impl;

import org.portfolio.competitormanager.connection.ConnectionToDB;
import org.portfolio.competitormanager.dao.ResultDao;
import org.portfolio.competitormanager.model.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultDaoImpl implements ResultDao {

    @Override
    public int save(Result result) throws SQLException, ClassNotFoundException {
        String SQLQuery = "INSERT INTO results (user_id, username, score, difficulty) VALUES (?,?,?,?)";
        try(Connection connection = ConnectionToDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery)) {
            preparedStatement.setInt(1, result.getUserId());
            preparedStatement.setString(2, result.getUsername());
            preparedStatement.setInt(3, result.getScore());
            preparedStatement.setString(4, result.getDifficulty());
            return preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Result> findByUserId(int user_id) throws SQLException, ClassNotFoundException {
        String selectQuery = "SELECT * FROM results WHERE user_id=?";
        List<Result> results = new ArrayList<Result>();
        try(Connection connection = ConnectionToDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1,user_id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    results.add(new Result(
                            resultSet.getInt("result_id"),
                            resultSet.getInt("user_id"),
                            resultSet.getString("username"),
                            resultSet.getInt("score"),
                            resultSet.getString("difficulty")

                    ));
                }
        }
        return results;
    }
}
