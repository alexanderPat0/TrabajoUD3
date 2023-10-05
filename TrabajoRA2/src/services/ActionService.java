package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import models.Action;
import models.Product;

public class ActionService {
	
	private final String table = "actions";

	public void save(Connection conexion, Action action) throws SQLException {
		try {
			PreparedStatement consult;
			if (action.getId() == null) {
				LocalDateTime localDate = LocalDateTime.now().plusMonths(3);
				consult = conexion.prepareStatement("INSERT INTO " + this.table
						+ "(id_user, id_product, id_provider, action_tipe, date) VALUES(?, ?, ?, ?, ?)");

				consult.setInt(1, action.getId_user());
				consult.setInt(2, action.getId_product());
				consult.setInt(3, action.getId_provider());
				consult.setInt(4, action.getAction_tipe());
				Date sqlDate = Date.valueOf(localDate.toLocalDate());
				consult.setDate(7, sqlDate);
			} else {
				consult = conexion.prepareStatement("UPDATE " + this.table
						+ " SET id_user = ?, id_product = ?, id_provider = ?, action_tipe = ?, date = ? WHERE id = ?");
				consult.setInt(1, action.getId_user());
				consult.setInt(2, action.getId_product());
				consult.setInt(3, action.getId_provider());
				consult.setInt(4, action.getAction_tipe());
				consult.setDate(5, action.getDate());
				consult.setInt(6, action.getId());
			}
			consult.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	public Action getAction(Connection conexion, int id) throws SQLException {
		Action action = null;
		try {
			PreparedStatement consult = conexion.prepareStatement(
					"SELECT id, id_user, id_product, id_provider, action_tipe, date" + " FROM "
							+ this.table + " WHERE id = ?");
			consult.setInt(1, id);
			ResultSet result = consult.executeQuery();
			while (result.next()) {
				action = new Action(result.getInt("id"), result.getInt("id_user"), result.getInt("id_product"),
						result.getInt("id_provider"), result.getInt("action_tipe"), result.getDate("date"));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return action;
	}

	public void remove(Connection conexion, Integer id) throws SQLException {
		try {
			PreparedStatement consult = conexion.prepareStatement("DELETE FROM " + this.table + " WHERE id = ?");
			consult.setInt(1, id);
			consult.executeUpdate();
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
	}

	public List<Action> getAllActions(Connection conexion) throws SQLException {
		List<Action> actionList = new ArrayList<>();
		try {
			PreparedStatement consult = conexion.prepareStatement(

					"SELECT id , id_user, id_product, id_provider, action_tipe, date" + " FROM "
							+ this.table);
			ResultSet result = consult.executeQuery();
			while (result.next()) {
				actionList.add(new Action(result.getInt("id"), result.getInt("id_user"), result.getInt("id_product"),
						result.getInt("id_provider"), result.getInt("action_tipe"), result.getDate("expire_date")));
			}
		} catch (SQLException ex) {
			throw new SQLException(ex);
		}
		return actionList;
	}

}


