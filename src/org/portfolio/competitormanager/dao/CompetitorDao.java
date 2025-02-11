package org.portfolio.competitormanager.dao;

import org.portfolio.competitormanager.model.Competitor;

import java.sql.SQLException;

/**
 * DAO interface to manage competitors in the database
 * provides the methods for saving and retrieving competitor data
 */
public interface CompetitorDao {

	int save(Competitor competitor) throws SQLException, ClassNotFoundException;

	Competitor findByUsername(String username) throws SQLException, ClassNotFoundException;

}