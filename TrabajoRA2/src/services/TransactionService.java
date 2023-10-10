package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import models.Transaction;

public class TransactionService {

	
	/** The table. */
	private final String table = "transactions";

	public void save(Connection conexion, Transaction transaction) throws SQLException {
		try {
			PreparedStatement consult;
			if (transaction.getId() == null) {
				LocalDateTime localDate = LocalDateTime.now().plusMonths(3);
				consult = conexion.prepareStatement("INSERT INTO " + this.table
						+ "(id_prod, price, amount) VALUES(?, ?, ?)");

				consult.setInt(1, transaction.getId_prod());
				consult.setFloat(2,transaction.getPrice());
				consult.setInt(3, transaction.getAmount());

			} else {
				consult = conexion.prepareStatement("UPDATE " + this.table
						+ " SET id_prod = ?, price = ?, amount = ? WHERE id = ?");
				consult.setInt(1, transaction.getId_prod());
				consult.setFloat(2,transaction.getPrice());
				consult.setInt(3, transaction.getAmount());
				consult.setInt(4, transaction.getId());

			}
			consult.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	public Transaction getProduct(Connection conexion, int id) throws SQLException {
		Transaction transaction = null;
		try {
			PreparedStatement consult = conexion.prepareStatement(
					"SELECT id, id_prod, price, amount"
							+ " FROM " + this.table + " WHERE id = ?");
			consult.setInt(1, id);
			ResultSet result = consult.executeQuery();
			while (result.next()) {
				transaction = new Transaction(result.getInt("id"), result.getInt("id_prod"), result.getFloat("price"), result.getInt("amount"));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return transaction;
	}

	public void remove(Connection conexion, Integer id) throws SQLException {
		PreparedStatement consult;
		try {
			consult = conexion.prepareStatement("UPDATE " + this.table + " SET available = ? WHERE id = ?");
			consult.setInt(1, 0);
			consult.setInt(2, id);
			consult.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	public List<Transaction> getAllTransactions(Connection conexion) throws SQLException {
		List<Transaction> transactionList = new ArrayList<>();
		try {
			PreparedStatement consult = conexion.prepareStatement(

					"SELECT id, id_prod, price, amount"
							+ " FROM " + this.table);
			ResultSet result = consult.executeQuery();
			while (result.next()) {
				transactionList.add(new Transaction(result.getInt("id"), result.getInt("id_prod"), result.getFloat("price"), result.getInt("amount")));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return transactionList;
	}
	
}
