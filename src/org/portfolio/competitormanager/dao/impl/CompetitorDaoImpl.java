package org.portfolio.competitormanager.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.portfolio.competitormanager.connection.ConnectionToDB;
import org.portfolio.competitormanager.dao.CompetitorDao;
import org.portfolio.competitormanager.model.Competitor;

public class CompetitorDaoImpl implements CompetitorDao {

	@Override
	public int save(Competitor competitor) throws SQLException, ClassNotFoundException {
		String SQLQuery = "INSERT INTO Competitors (competitor_id, name, age,  country, level, scores, average) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try(Connection conn = ConnectionToDB.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SQLQuery)){
			
			stmt.setInt(1, competitor.getCompetitorId());
			stmt.setString(2, competitor.getName().getFullName());
			stmt.setInt(3,  competitor.getAge());
			stmt.setString(4, competitor.getCountry());
			stmt.setString(5, competitor.getLevel());
			stmt.setString(6, Arrays.stream(competitor.getScores()).mapToObj(String::valueOf).collect(Collectors.joining(",")));
			stmt.setDouble(7,  competitor.getOverallScore());
			
			return stmt.executeUpdate();
		}
		
	}

	@Override
	public int update(Competitor competitor, int competitor_id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(int competitor_id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Competitor findOne(int competitor_id) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Competitor> findAll() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Competitor> search(String SQLQuery) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
