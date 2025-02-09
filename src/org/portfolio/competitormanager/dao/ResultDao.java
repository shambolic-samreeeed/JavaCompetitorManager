package org.portfolio.competitormanager.dao;

import org.portfolio.competitormanager.model.Result;
import java.util.List;
import java.sql.SQLException;

public interface ResultDao {

    int save(Result result) throws SQLException, ClassNotFoundException;

    List<Result> findByUserId(int user_id) throws SQLException, ClassNotFoundException;
}
