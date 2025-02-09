package org.portfolio.competitormanager.dao;

import org.portfolio.competitormanager.model.Questions;

import java.sql.SQLException;
import java.util.List;

public interface QuestionDao {

    int save(Questions questions) throws SQLException, ClassNotFoundException;

    int update(Questions question, int question_id) throws SQLException, ClassNotFoundException;

    int delete(int question_id) throws SQLException, ClassNotFoundException;

    List<Questions> findAll() throws SQLException, ClassNotFoundException;

    List<Questions> findByDifficulty(String difficulty) throws SQLException, ClassNotFoundException;
}
