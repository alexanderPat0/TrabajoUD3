package models;

import java.sql.Date;

public class Product {
	
	private int id_prov;
	private Integer id;
	private String name, description, category, image;
	private Date expire_date;
	private float price;
	
	public Product(Integer id, int id_prov, String name, String description, float price, String category, String image, Date expire_date) {
		super();
		this.id = id;
		this.id_prov = id_prov;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.image = image;
		this.expire_date = expire_date;
	}

	public Product(int id_prov, String name, String description,float price, String category, String image, Date expire_date) {
		super();
		this.id_prov = id_prov;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.image = image;
		this.expire_date = expire_date;
	}

	public Product() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getId_prov() {
		return id_prov;
	}

	public void setId_prov(int id_prov) {
		this.id_prov = id_prov;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getExpire_date() {
		return expire_date;
	}

	public void setExpire_date(Date expire_date) {
		this.expire_date = expire_date;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", id_prov=" + id_prov + ", name=" + name + ", description=" + description
				+ ", category=" + category + ", image=" + image + ", expire_date=" + expire_date + ", price=" + price
				+ "]";
	}
	
	
	
	
	

}
