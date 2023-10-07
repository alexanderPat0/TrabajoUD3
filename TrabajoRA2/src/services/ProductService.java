package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import models.Product;

// TODO: Auto-generated Javadoc
/**
 * The Class ProductService.
 */
public class ProductService {

	/** The table. */
	private final String table = "products";

	public void save(Connection conexion, Product product) throws SQLException {
		try {
			PreparedStatement consult;
			if (product.getId() == null) {
				LocalDateTime localDate = LocalDateTime.now().plusMonths(3);
				consult = conexion.prepareStatement("INSERT INTO " + this.table
						+ "(id_prov, name, description, price, amount, category, image, expire_date) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");

				consult.setInt(1, product.getId_prov());
				consult.setString(2, product.getName());
				consult.setString(3, product.getDescription());
				consult.setFloat(4, product.getPrice());
				consult.setFloat(4, product.getAmount());
				consult.setString(5, product.getCategory());
				consult.setString(6, product.getImage());
				Date sqlDate = Date.valueOf(localDate.toLocalDate());
				consult.setDate(7, sqlDate);
			} else {
				consult = conexion.prepareStatement("UPDATE " + this.table
						+ " SET id_prov = ?, name = ?, description = ?, price = ?, amount = ? , category = ?, image = ?, expire_date = ? WHERE id = ?");
				consult.setInt(1, product.getId_prov());
				consult.setString(2, product.getName());
				consult.setString(3, product.getDescription());
				consult.setFloat(4, product.getPrice());
				consult.setInt(5, product.getAmount());
				consult.setString(6, product.getCategory());
				consult.setString(7, product.getImage());
				consult.setDate(8, product.getExpire_date());
				consult.setInt(9, product.getId());
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
					"SELECT id, id_prov, name, description, price, amount, category, image, expire_date" + " FROM "
							+ this.table + " WHERE id = ?");
			consult.setInt(1, id);
			ResultSet result = consult.executeQuery();
			while (result.next()) {
				product = new Product(result.getInt("id"), result.getInt("id_prov"), result.getString("name"),
						result.getString("description"), result.getFloat("price"), result.getInt("amount"),
						result.getString("category"), result.getString("image"), result.getDate("expire_date"));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return product;
	}

	// he cambiado Product product por Integer id
	public void remove(Connection conexion, Integer id) throws SQLException {
		try {
			PreparedStatement consult = conexion.prepareStatement("DELETE FROM " + this.table + " WHERE id = ?");
			consult.setInt(1, id);
			consult.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	public List<Product> getAllProducts(Connection conexion) throws SQLException {
		List<Product> productList = new ArrayList<>();
		try {
			PreparedStatement consult = conexion.prepareStatement(

					"SELECT id , id_prov, name, description, price, amount, category, image, expire_date" + " FROM "
							+ this.table);
			ResultSet result = consult.executeQuery();
			while (result.next()) {
				productList.add(new Product(result.getInt("id"), result.getInt("id_prov"), result.getString("name"),
						result.getString("description"), result.getFloat("price"), result.getInt("amount"),
						result.getString("category"), result.getString("image"), result.getDate("expire_date")));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return productList;
	}
	
	public Integer getProductID(Connection conexion, String name) throws SQLException {
		int id=0;
		try {
			PreparedStatement consult = conexion.prepareStatement(
					"SELECT id"
							+ " FROM " + this.table + " WHERE name = ?");
			//consult.setInt(1, id_prov);
			consult.setString(1, name);
			ResultSet result = consult.executeQuery();
			while (result.next()) {
				id=result.getInt("id");
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return id;
	}

}
