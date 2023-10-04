package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.User;

public class UserService {

	/** The table. */
	private final  String table = "users";

	public  void save(Connection conexion, User user) throws SQLException {
		try {
			PreparedStatement consult;
			if (user.getId() == null) {
				consult = conexion.prepareStatement("INSERT INTO " + table
						+ "(name, age, username, password) VALUES(?, ?, ?, ?)");
				consult.setString(1, user.getName());
				consult.setInt(2, user.getAge());
				consult.setString(3, user.getUsername());
				consult.setString(4, user.getPassword());
			} else {
				consult = conexion.prepareStatement("UPDATE " + table
						+ " SET id = ?, name = ?, age = ?, username = ?, password = ? WHERE id = ?");
				consult.setString(1, user.getName());
				consult.setInt(2, user.getAge());
				consult.setString(3, user.getUsername());
				consult.setString(4, user.getPassword());
				consult.setInt(5, user.getId());
			}
			consult.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	
	public User getUser(Connection conexion, int id) throws SQLException {
		User user = null;
		try {
			PreparedStatement consult = conexion.prepareStatement(
					"SELECT id, name, age, username, password"
							+ " FROM " + this.table + " WHERE id = ?");
			consult.setInt(1, id);
			ResultSet result = consult.executeQuery();
			while (result.next()) {
				user = new User(result.getInt("id"), result.getString("name"), result.getInt("age"), 
						result.getString("username"), result.getString("password"));
			} 
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return user;
	}

	
	public void remove(Connection conexion, User provider) throws SQLException {
		try {
			PreparedStatement consult = conexion
					.prepareStatement("DELETE FROM " + this.table + " WHERE id = ?");
			consult.setInt(1, provider.getId());
			consult.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

 
	public List<User> getAllUsers(Connection conexion) throws SQLException {
		List<User> userList = new ArrayList<>();
		try {
			PreparedStatement consult = conexion.prepareStatement(
					"SELECT id, name, age, username, password"
							+ " FROM " + this.table);
			ResultSet result = consult.executeQuery();
			while (result.next()) {
				userList.add(new User(result.getInt("id"), result.getString("name"), result.getInt("age"), 
						result.getString("username"), result.getString("password")));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return userList;
	}
	
	public User checkUser(Connection conexion, String username) throws SQLException {
		User user = null;
		try {
			PreparedStatement consult = conexion.prepareStatement(
					"SELECT id, name, age, username, password"
							+ " FROM " + this.table + " WHERE username = ?");
			consult.setString(1, username);
			ResultSet result = consult.executeQuery();
			while (result.next()) {
				user = new User(result.getInt("id"), result.getString("name"), result.getInt("age"), 
						result.getString("username"), result.getString("password"));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return user;		
	}
}
