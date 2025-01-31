package org.portfolio.competitormanager.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {

	private static final String username = "root";
	
	private static final String password = "";
	
	private static final String url = "jdbc:mysql://localhost:3306/competitiondb";
	
	private static final String DriverClassName = "com.mysql.cj.jdbc.Driver";
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(DriverClassName);
		
		return DriverManager.getConnection(url, username, password);
	}
	
	public static void main(String args[]) throws SQLException, ClassNotFoundException{
		Connection connection = ConnectionToDB.getConnection();
		System.out.println(connection);
		System.out.println("Connection to database sucessful.");
	}
}
