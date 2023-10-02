package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Product;
import models.Provider;
import models.User;

// TODO: Auto-generated Javadoc
/**
 * The Class ProductService.
 */
public class ProductService {

	/** The table. */
	private final String table = "product";

	public void save(Connection conexion, Product product) throws SQLException {
		try {
			PreparedStatement consult;
			if (product.getId() == null) {
				consult = conexion.prepareStatement("INSERT INTO " + this.table
						+ "(id_prov, name, description, price, category, image, expire_date) VALUES(?, ?, ?, ?, ?, ?, ?)");
				consult.setInt(1, product.getId_prov());
				consult.setString(2, product.getName());
				consult.setString(3, product.getDescription());
				consult.setFloat(4, product.getPrice());
				consult.setString(5, product.getCategory());
				consult.setString(6, product.getImage());
				consult.setDate(7, product.getExpire_date());
			} else {
				consult = conexion.prepareStatement("UPDATE " + this.table
						+ " SET id_prov = ?, name = ?, description = ?, price = ?, category = ?, image = ?, expire_date = ? WHERE id = ?");
				consult.setInt(1, product.getId_prov());
				consult.setString(2, product.getName());
				consult.setString(3, product.getDescription());
				consult.setFloat(4, product.getPrice());
				consult.setString(5, product.getCategory());
				consult.setString(6, product.getImage());
				consult.setDate(7, product.getExpire_date());
				consult.setInt(8, product.getId());
			}
			consult.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	
	public Product getProduct(Connection conexion, int id) throws SQLException {
		Product product = null;
		try {
			PreparedStatement consult = conexion.prepareStatement(
					"SELECT id, id_prov, name, description, price, category, image, expire_date"
							+ " FROM " + this.table + " WHERE id = ?");
			consult.setInt(1, id);
			ResultSet result = consult.executeQuery();
			while (result.next()) {
				product = new Product(result.getInt("id"), result.getInt("id_prov"),
						result.getString("name"), result.getString("description"), result.getFloat("price"),
						result.getString("category"), result.getString("image"), result.getDate("expire_date"));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return product;
	}

	
	public void remove(Connection conexion, Product product) throws SQLException {
		try {
			PreparedStatement consult = conexion
					.prepareStatement("DELETE FROM " + this.table + " WHERE id = ?");
			consult.setInt(1, product.getId());
			consult.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}


	public List<Product> getAllProducts(Connection conexion) throws SQLException {
		List<Product> productList = new ArrayList<>();
		try {
			PreparedStatement consult = conexion.prepareStatement(
					"SELECT id, id_prov, name, description, price, category, image, expire_date"
							+ " FROM " + this.table);
			ResultSet result = consult.executeQuery();
			while (result.next()) {
				productList.add(new Product(result.getInt("id"), result.getInt("id_prov"),
						result.getString("name"), result.getString("description"), result.getFloat("price"),
						result.getString("category"), result.getString("image"), result.getDate("expire_date")));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return productList;
	}

	
	public List<User> getAllUsers(Connection conexion) throws SQLException {
		List<User> userList = new ArrayList<>();
		// int id, String name, int age,  String username, String password
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("SELECT id, name, age, username, password " + " FROM "
							+ this.table + " ORDER BY id");
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				userList.add(new User(resultado.getInt("id"), resultado.getString("name"), 
						resultado.getInt("age"), resultado.getString("username"), resultado.getString("password")));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return userList;
	}

	
	public List<Provider> getAllProviders(Connection conexion) throws SQLException {
		List<Provider> providerList = new ArrayList<>();
		// Integer id,  String name, String location, String mail, int phone
		try {
			PreparedStatement consulta = conexion
					.prepareStatement("SELECT id, name, location, mail, phone " + " FROM "
							+ this.table + " ORDER BY id");
			ResultSet resultado = consulta.executeQuery();
			while (resultado.next()) {
				providerList.add(new Provider(resultado.getInt("id"), resultado.getString("name"),
						resultado.getString("location"), resultado.getString("mail"), resultado.getInt("phone")));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return providerList;
	}


}
