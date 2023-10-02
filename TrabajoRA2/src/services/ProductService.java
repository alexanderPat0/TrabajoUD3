package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Product;

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
			if (product.getId() == 0) {
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
				product = new Product(result.getInt("id"), result.getInt("id_prod"),
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
				productList.add(new Product(result.getInt("id"), result.getInt("id_prod"),
						result.getString("name"), result.getString("description"), result.getFloat("price"),
						result.getString("category"), result.getString("image"), result.getDate("expire_date")));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return productList;
	}

	
//	public List<Vehiculo> getAllVehiculosMarca(Connection conexion) throws SQLException {
//		List<Vehiculo> listaVehiculos = new ArrayList<>();
//		try {
//			PreparedStatement consulta = conexion
//					.prepareStatement("SELECT idVehiculos, modelo, marca, anyo, color, precio, idFabricante " + " FROM "
//							+ this.tabla + " ORDER BY marca");
//			ResultSet resultado = consulta.executeQuery();
//			while (resultado.next()) {
//				listaVehiculos.add(new Vehiculo(resultado.getInt("idVehiculos"), resultado.getString("modelo"),
//						resultado.getString("marca"), resultado.getInt("anyo"), resultado.getString("color"),
//						resultado.getFloat("precio"), resultado.getInt("idFabricante")));
//			}
//		} catch (SQLException ex) {
//			throw new SQLException(ex);
//		}
//		return listaVehiculos;
//	}

	
//	public List<Vehiculo> getAllVehiculosModelo(Connection conexion) throws SQLException {
//		List<Vehiculo> listaVehiculos = new ArrayList<>();
//		try {
//			PreparedStatement consulta = conexion
//					.prepareStatement("SELECT idVehiculos, modelo, marca, anyo, color, precio, idFabricante " + " FROM "
//							+ this.tabla + " ORDER BY modelo");
//			ResultSet resultado = consulta.executeQuery();
//			while (resultado.next()) {
//				listaVehiculos.add(new Vehiculo(resultado.getInt("idVehiculos"), resultado.getString("modelo"),
//						resultado.getString("marca"), resultado.getInt("anyo"), resultado.getString("color"),
//						resultado.getFloat("precio"), resultado.getInt("idFabricante")));
//			}
//		} catch (SQLException ex) {
//			throw new SQLException(ex);
//		}
//		return listaVehiculos;
//	}

//	public List<Vehiculo> getAllVehiculosAnyo(Connection conexion) throws SQLException {
//		List<Vehiculo> listaVehiculos = new ArrayList<>();
//		try {
//			PreparedStatement consulta = conexion
//					.prepareStatement("SELECT idVehiculos, modelo, marca, anyo, color, precio, idFabricante " + " FROM "
//							+ this.tabla + " ORDER BY anyo");
//			ResultSet resultado = consulta.executeQuery();
//			while (resultado.next()) {
//				listaVehiculos.add(new Vehiculo(resultado.getInt("idVehiculos"), resultado.getString("modelo"),
//						resultado.getString("marca"), resultado.getInt("anyo"), resultado.getString("color"),
//						resultado.getFloat("precio"), resultado.getInt("idFabricante")));
//			}
//		} catch (SQLException ex) {
//			throw new SQLException(ex);
//		}
//		return listaVehiculos;
//	}

	
//	public List<Vehiculo> getAllVehiculosColor(Connection conexion) throws SQLException {
//		List<Vehiculo> listaVehiculos = new ArrayList<>();
//		try {
//			PreparedStatement consulta = conexion
//					.prepareStatement("SELECT idVehiculos, modelo, marca, anyo, color, precio, idFabricante " + " FROM "
//							+ this.tabla + " ORDER BY color");
//			ResultSet resultado = consulta.executeQuery();
//			while (resultado.next()) {
//				listaVehiculos.add(new Vehiculo(resultado.getInt("idVehiculos"), resultado.getString("modelo"),
//						resultado.getString("marca"), resultado.getInt("anyo"), resultado.getString("color"),
//						resultado.getFloat("precio"), resultado.getInt("idFabricante")));
//			}
//		} catch (SQLException ex) {
//			throw new SQLException(ex);
//		}
//		return listaVehiculos;
//	}

//	public List<Vehiculo> getAllVehiculosPrecio(Connection conexion) throws SQLException {
//		List<Vehiculo> listaVehiculos = new ArrayList<>();
//		try {
//			PreparedStatement consulta = conexion
//					.prepareStatement("SELECT idVehiculos, modelo, marca, anyo, color, precio, idFabricante " + " FROM "
//							+ this.tabla + " ORDER BY precio");
//			ResultSet resultado = consulta.executeQuery();
//			while (resultado.next()) {
//				listaVehiculos.add(new Vehiculo(resultado.getInt("idVehiculos"), resultado.getString("modelo"),
//						resultado.getString("marca"), resultado.getInt("anyo"), resultado.getString("color"),
//						resultado.getFloat("precio"), resultado.getInt("idFabricante")));
//			}
//		} catch (SQLException ex) {
//			throw new SQLException(ex);
//		}
//		return listaVehiculos;
//	}

	
//	public List<Vehiculo> getAllVehiculosidFabricante(Connection conexion) throws SQLException {
//		List<Vehiculo> listaVehiculos = new ArrayList<>();
//		try {
//			PreparedStatement consulta = conexion
//					.prepareStatement("SELECT idVehiculos, modelo, marca, anyo, color, precio, idFabricante " + " FROM "
//							+ this.tabla + " ORDER BY idFabricante");
//			ResultSet resultado = consulta.executeQuery();
//			while (resultado.next()) {
//				listaVehiculos.add(new Vehiculo(resultado.getInt("idVehiculos"), resultado.getString("modelo"),
//						resultado.getString("marca"), resultado.getInt("anyo"), resultado.getString("color"),
//						resultado.getFloat("precio"), resultado.getInt("idFabricante")));
//			}
//		} catch (SQLException ex) {
//			throw new SQLException(ex);
//		}
//		return listaVehiculos;
//	}

	
//	public List<Vehiculo> getAllVehiculosFabric(Connection conexion) throws SQLException {
//		List<Vehiculo> listaVehiculos = new ArrayList<>();
//		try {
//			PreparedStatement consulta = conexion
//					.prepareStatement("SELECT idVehiculos, modelo, marca, anyo, color, precio, idFabricante " + " FROM "
//							+ this.tabla + " WHERE idFabricante = ?");
//			consulta.setInt(1, ListViewFabricante.getidFabricanteCrear());
//			ResultSet resultado = consulta.executeQuery();
//			while (resultado.next()) {
//				listaVehiculos.add(new Vehiculo(resultado.getInt("idVehiculos"), resultado.getString("modelo"),
//						resultado.getString("marca"), resultado.getInt("anyo"), resultado.getString("color"),
//						resultado.getFloat("precio"), resultado.getInt("idFabricante")));
//			}
//		} catch (SQLException ex) {
//			throw new SQLException(ex);
//		}
//		return listaVehiculos;
//	}

	
//	public List<Vehiculo> getAllVehiculosCliente(Connection conexion) throws SQLException {
//		List<Vehiculo> listaVehiculos = new ArrayList<>();
//		try {
//			PreparedStatement consulta = conexion.prepareStatement(
//					"SELECT v.idVenta, v.fechaHora, ve.idVehiculos, ve.Modelo, ve.Marca, ve.Anyo, ve.Color, ve.Precio, ve.idFabricante "
//							+ "FROM venta v, vehiculo ve " + "WHERE v.idVehiculo = ve.idVehiculos "
//							+ "AND v.idCliente = ?;");
//			consulta.setInt(1, Login.getidClienteLogin());
//			ResultSet resultado = consulta.executeQuery();
//			while (resultado.next()) {
//				listaVehiculos.add(new Vehiculo(resultado.getInt("idVehiculos"), resultado.getString("modelo"),
//						resultado.getString("marca"), resultado.getInt("anyo"), resultado.getString("color"),
//						resultado.getFloat("precio"), resultado.getInt("idFabricante")));
//			}
//		} catch (SQLException ex) {
//			throw new SQLException(ex);
//		}
//		return listaVehiculos;
//	}

}
