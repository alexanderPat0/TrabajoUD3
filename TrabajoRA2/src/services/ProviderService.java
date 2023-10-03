package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Provider;

public class ProviderService {

	/** The table. */
	private final static String table = "providers";

	public static void save(Connection conexion, Provider provider) throws SQLException {
		try {
			PreparedStatement consult;
			if (provider.getId() == null) {
				consult = conexion.prepareStatement("INSERT INTO " + table
						+ "(name, location, mail, phone) VALUES(?, ?, ?, ?)");
				consult.setString(1, provider.getName());
				consult.setString(2, provider.getLocation());
				consult.setString(3, provider.getMail());
				consult.setInt(4, provider.getPhone());
			} else {
				consult = conexion.prepareStatement("UPDATE " + table
						+ " SET id = ?, name = ?, location = ?, mail = ?, phone = ? WHERE id = ?");
				consult.setString(1, provider.getName());
				consult.setString(2, provider.getLocation());
				consult.setString(3, provider.getMail());
				consult.setInt(4, provider.getPhone());
				consult.setInt(5, provider.getId());
			}
			consult.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	
	public Provider getProvider(Connection conexion, int id) throws SQLException {
		Provider provider = null;
		try {
			PreparedStatement consult = conexion.prepareStatement(
					"SET id, name, location, mail, phone"
							+ " FROM " + this.table + " WHERE id = ?");
			consult.setInt(1, id);
			ResultSet result = consult.executeQuery();
			while (result.next()) {
				provider = new Provider(result.getInt("id"), result.getString("name"), result.getString("location"), 
						result.getString("mail"), result.getInt("phone"));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return provider;
	}

	
	public void remove(Connection conexion, Provider provider) throws SQLException {
		try {
			PreparedStatement consult = conexion
					.prepareStatement("DELETE FROM " + this.table + " WHERE id = ?");
			consult.setInt(1, provider.getId());
			consult.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}


	public List<Provider> getAllProviders(Connection conexion) throws SQLException {
		List<Provider> providerList = new ArrayList<>();
		try {
			PreparedStatement consult = conexion.prepareStatement(
					"SELECT id, name, location, mail, phone"
							+ " FROM " + this.table);
			ResultSet result = consult.executeQuery();
			while (result.next()) {
				providerList.add(new Provider(result.getInt("id"), result.getString("name"), result.getString("location"), 
						result.getString("mail"), result.getInt("phone")));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return providerList;
	}
}
