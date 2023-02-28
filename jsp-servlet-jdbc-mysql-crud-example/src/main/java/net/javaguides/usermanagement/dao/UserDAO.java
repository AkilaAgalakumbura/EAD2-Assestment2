package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.User;

public class UserDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	
	private static final String INSERT_USERS_SQL = "INSERT INTO users" + "(Name, Department, Designation, Joined_Date)VALUES"
			+ " (?, ?, ?, ?);";
	
	private static final String SELECT_USER_BY_NIC = "select Name,NIC,Department,Designation,Joined_Date from users where NIC =?";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String DELETE_USERS_SQL = "delete from users where NIC = ?;";
	private static final String UPDATE_USERS_SQL = "update users set name = ?,Department= ?,Designation= ?,Joined_Date =? where NIC = ?;";

	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	//Create or Insert User
	public void insertUser(User user) throws SQLException{
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getDepartment());
			preparedStatement.setString(3, user.getDesignation());
			preparedStatement.setString(4, user.getJoined_Date());
			preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Update User
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);){
			statement.setString(1, user.getName());
			statement.setString(2, user.getDepartment());
			statement.setString(3, user.getDesignation());
			statement.setString(4, user.getJoined_Date());
			statement.setInt(5, user.getNIC());
			
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	//Update User by NIC
	public User selectUser(int NIC) {
		User user = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_NIC);) {
			preparedStatement.setInt(1, NIC);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				String Name = rs.getString("Name");
				String Department = rs.getString("Department");
				String Designation = rs.getString("Designation");
				String Joined_Date = rs.getString("Joined_Date");
				user = new User(Name, NIC, Department, Designation, Joined_Date);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	//Select User
	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				String Name = rs.getString("Name");
				int NIC = rs.getInt("NIC");
				String Department = rs.getString("Department");
				String Designation = rs.getString("Designation");
				String Joined_Date = rs.getString("Joined_Date");
				users.add(new User(Name, NIC, Department, Designation, Joined_Date));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	//Delete User
	public boolean deleteUser(int NIC) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, NIC);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
}
