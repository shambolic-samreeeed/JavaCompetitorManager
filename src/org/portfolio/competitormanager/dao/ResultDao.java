package org.portfolio.competitormanager.dao;

import org.portfolio.competitormanager.model.Result;
import java.util.List;
import java.sql.SQLException;

/**
 * DAO interface to manage results of the competitors in the database
 * provides the methods for saving and retrieving competitor data
 */

public interface ResultDao {

    int save(Result result) throws SQLException, ClassNotFoundException;

    List<Result> findByUserId(int user_id) throws SQLException, ClassNotFoundException;

    List<Result> findAll() throws SQLException, ClassNotFoundException;

}