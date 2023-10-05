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
	private final  String table = "providers";

	public void save(Connection conexion, Provider provider) throws SQLException {
		try {
			PreparedStatement consult;
			if (provider.getId() == null) {
				consult = conexion.prepareStatement("INSERT INTO " + this.table
						+ "(name, location, mail, phone) VALUES(?, ?, ?, ?)");
				consult.setString(1, provider.getName());
				consult.setString(2, provider.getLocation());
				consult.setString(3, provider.getMail());
				consult.setInt(4, provider.getPhone());
			} else {
				consult = conexion.prepareStatement("UPDATE " + this.table
						+ " SET id = ?, name = ?, location = ?, mail = ?, phone = ? WHERE id = ?");
				consult.setInt(1, provider.getId());
				consult.setString(2, provider.getName());
				consult.setString(3, provider.getLocation());
				consult.setString(4, provider.getMail());
				consult.setInt(5, provider.getPhone());
				consult.setInt(6, provider.getId());
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
					"SELECT id, name, location, mail, phone"
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

	
	public void remove(Connection conexion, Integer id) throws SQLException {
		try {
			PreparedStatement consult = conexion
					.prepareStatement("DELETE FROM " + this.table + " WHERE id = ?");
			consult.setInt(1, id);
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
	public List<String>getNameProvs(Connection conexion) throws SQLException{
		List<String>nameProvs=new ArrayList<>();
		try {
			PreparedStatement consult = conexion.prepareStatement(
					"SELECT id, name"
							+ " FROM " + this.table);
			ResultSet result = consult.executeQuery();
			while (result.next()) {
				nameProvs.add(result.getString("name"));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return nameProvs;
	}
	
	public Integer getProviderID(Connection conexion, String name) throws SQLException {
		int id_prov=0;
		try {
			PreparedStatement consult = conexion.prepareStatement(
					"SELECT id"
							+ " FROM " + this.table + " WHERE name = ?");
			//consult.setInt(1, id_prov);
			consult.setString(1, name);
			ResultSet result = consult.executeQuery();
			while (result.next()) {
				id_prov=result.getInt("id");
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return id_prov;
	}
	
}
