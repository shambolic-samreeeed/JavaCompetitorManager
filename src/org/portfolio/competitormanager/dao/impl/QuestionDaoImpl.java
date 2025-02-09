package org.portfolio.competitormanager.dao.impl;

import org.portfolio.competitormanager.connection.ConnectionToDB;
import org.portfolio.competitormanager.dao.QuestionDao;
import org.portfolio.competitormanager.model.Questions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    @Override
    public int save(Questions questions) throws SQLException, ClassNotFoundException {
        String SQLQuery = "INSERT INTO Questions (question_text, option1, option2, option3, option4, correct_option, difficulty) VALUES (?,?,?,?,?,?,?)";
        try(Connection connection = ConnectionToDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQLQuery)) {
            preparedStatement.setString(1, questions.getQuestionText());
            preparedStatement.setString(2, questions.getOption1());
            preparedStatement.setString(3, questions.getOption2());
            preparedStatement.setString(4, questions.getOption3());
            preparedStatement.setString(5, questions.getOption4());
            preparedStatement.setInt(6, questions.getCorrectOption());
            preparedStatement.setString(7, questions.getDifficulty());
            return preparedStatement.executeUpdate();
        }
    }



    @Override
    public int update(Questions question, int question_id) throws SQLException, ClassNotFoundException {
        String updateSQL = "UPDATE Questions SET question_text=?, option1=?, option2=?, option3=?, option4=?, correct_option=?, difficulty=? WHERE question_id=?";
        try(Connection connection = ConnectionToDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, question.getQuestionText());
            preparedStatement.setString(2, question.getOption1());
            preparedStatement.setString(3, question.getOption2());
            preparedStatement.setString(4, question.getOption3());
            preparedStatement.setString(5, question.getOption4());
            preparedStatement.setInt(6, question.getCorrectOption());
            preparedStatement.setString(7, question.getDifficulty());
            preparedStatement.setInt(8, question_id);
            return preparedStatement.executeUpdate();
        }
    }

    @Override
    public int delete(int question_id) throws SQLException, ClassNotFoundException {
        String deleteSQL = "DELETE FROM Questions WHERE question_id=?";
        try(Connection connection = ConnectionToDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, question_id);
            return preparedStatement.executeUpdate();
        }

    }

    @Override
    public List<Questions> findAll() throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM Questions";
        List<Questions> questions = new ArrayList<Questions>();
        try(Connection connection = ConnectionToDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while(resultSet.next()){
                questions.add(new Questions(
                        resultSet.getInt("question_id"),
                        resultSet.getString("question_id"),
                        resultSet.getString("option1"),
                        resultSet.getString("option2"),
                        resultSet.getString("option3"),
                        resultSet.getString("option4"),
                        resultSet.getInt("correct_option"),
                        resultSet.getString("difficulty")
                ));
            }
        }
        return questions;
    }

    @Override
    public List<Questions> findByDifficulty(String difficulty) throws SQLException, ClassNotFoundException {
        String selectSQL = "SELECT * FROM Questions WHERE difficulty=?";
        List<Questions> questions = new ArrayList<>();
        try(Connection connection = ConnectionToDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, difficulty);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                questions.add(new Questions(
                        resultSet.getInt("question_id"),
                        resultSet.getString("question_text"),
                        resultSet.getString("option1"),
                        resultSet.getString("option2"),
                        resultSet.getString("option3"),
                        resultSet.getString("option4"),
                        resultSet.getInt("correct_option"),
                        resultSet.getString("difficulty")
                ));
            }
        }
        return questions;
    }
}
