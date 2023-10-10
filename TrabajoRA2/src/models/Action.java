package models;

import java.sql.Date;

public class Action {

	private Integer id;
	private int id_user, id_product, id_provider, action_tipe;
	private Date date;

	public Action(int id_user, int id_product, int id_provider, int action_tipe, Date date) {
		super();
		this.id_user = id_user;
		this.id_product = id_product;
		this.id_provider = id_provider;
		this.action_tipe = action_tipe;
		this.date = date;
	}

	public Action() {
		super();
	}

	public Action(Integer id, int id_user, int id_product, int id_provider, int action_tipe, Date date) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.id_product = id_product;
		this.id_provider = id_provider;
		this.action_tipe = action_tipe;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getId_product() {
		return id_product;
	}

	public void setId_product(int id_product) {
		this.id_product = id_product;
	}

	public int getId_provider() {
		return id_provider;
	}

	public void setId_provider(int id_provider) {
		this.id_provider = id_provider;
	}

	public int getAction_tipe() {
		return action_tipe;
	}

	public void setAction_tipe(int action_tipe) {
		this.action_tipe = action_tipe;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Action [id=" + id + ", id_user=" + id_user + ", id_product=" + id_product + ", id_provider="
				+ id_provider + ", action_tipe=" + action_tipe + ", date=" + date + "]";
	}

}