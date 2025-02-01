package org.portfolio.competitormanager.dao;

import java.sql.SQLException;
import java.util.List;

import org.portfolio.competitormanager.model.Competitor;

public interface CompetitorDao {


	    int save(Competitor competitor) throws SQLException, ClassNotFoundException;

	    int update(Competitor competitor, int competitor_id) throws SQLException, ClassNotFoundException;

	    int remove(int competitor_id) throws SQLException, ClassNotFoundException;

	    Competitor findOne(int competitor_id) throws SQLException, ClassNotFoundException;

	    List<Competitor> findAll() throws SQLException, ClassNotFoundException;

	    List<Competitor> search(String SQLQuery) throws SQLException, ClassNotFoundException;
	}


