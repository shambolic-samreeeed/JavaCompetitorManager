package org.portfolio.competitormanager.model;

/**
 * this class represents a user
 * it models a user with a unique id, username, password and role.
 */
public class Competitor {
	private int userId;
	private String username;
	private String password;
	private String role;

	/**
	 * constructs a new competitor with the given attributes
	 * @param userId the unique userid of the user
	 * @param username username of the user
	 * @param password	password that the user has selected
	 * @param role role of the user either admin or competitor(user)
	 */
	public Competitor(int userId, String username, String password, String role) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	//getter and setter methods.

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}