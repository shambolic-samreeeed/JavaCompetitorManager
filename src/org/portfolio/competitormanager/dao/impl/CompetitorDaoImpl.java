package org.portfolio.competitormanager.dao.impl;

import org.portfolio.competitormanager.connection.ConnectionToDB;
import org.portfolio.competitormanager.dao.CompetitorDao;
import org.portfolio.competitormanager.model.Competitor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompetitorDaoImpl implements CompetitorDao {

	@Override
	public int save(Competitor competitor) throws SQLException, ClassNotFoundException {
		String saveQuery = "INSERT INTO Users (username, password, role) VALUES (?, ?, ?)";
		try(Connection conn = ConnectionToDB.getConnection();
		PreparedStatement stmt = conn.prepareStatement(saveQuery)){
			stmt.setString(1, competitor.getUsername());
			stmt.setString(2, competitor.getPassword());
			stmt.setString(3, competitor.getRole());
			return stmt.executeUpdate();
		}
	}

	@Override
	public Competitor findByUsername(String username) throws SQLException, ClassNotFoundException {
		String selectQuery = "SELECT * FROM Users WHERE username = ?";
		try(Connection connection = ConnectionToDB.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
			preparedStatement.setString(1, username);
			ResultSet resultset = preparedStatement.executeQuery();
			if(resultset.next()){
				return new Competitor(resultset.getInt("user_id"), resultset.getString("username"), resultset.getString("password"), resultset.getString("role"));
			}
		}
		return null;
	}
}