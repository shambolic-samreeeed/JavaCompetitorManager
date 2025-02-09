package org.portfolio.competitormanager.dao;

import org.portfolio.competitormanager.model.Competitor;

import java.sql.SQLException;

public interface CompetitorDao {

	int save(Competitor competitor) throws SQLException, ClassNotFoundException;

	Competitor findByUsername(String username) throws SQLException, ClassNotFoundException;

}