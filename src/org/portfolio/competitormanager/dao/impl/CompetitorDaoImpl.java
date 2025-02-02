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
		String updateSQL = "update competitors SET name=?, age=?, country=?, level=?, scores=?, average=? WHERE competitor_id=?";
		
		try(Connection conn = ConnectionToDB.getConnection();
				PreparedStatement stmt = conn.prepareStatement(updateSQL)){
			
			
			stmt.setString(1, competitor.getName().getFullName());
			stmt.setInt(2,  competitor.getAge());
			stmt.setString(3, competitor.getCountry());
			stmt.setString(4, competitor.getLevel());
			stmt.setString(5, Arrays.stream(competitor.getScores()).mapToObj(String::valueOf).collect(Collectors.joining(",")));
			stmt.setDouble(6,  competitor.getOverallScore());
			stmt.setInt(7, competitor_id);
			
			return stmt.executeUpdate();
		}
	}

	@Override
	public int remove(int competitor_id) throws SQLException, ClassNotFoundException {
		String deleteSQL = "DELETE FROM Competitors WHERE competitor_id=?";
		
		try(Connection conn = ConnectionToDB.getConnection();
				PreparedStatement stmt = conn.prepareStatement(deleteSQL)){
			stmt.setInt(1, competitor_id);
			return stmt.executeUpdate();
		}
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
