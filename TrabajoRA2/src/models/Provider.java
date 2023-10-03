package models;

public class Provider {
	
	private int phone;
	private Integer id;
	private String name, location, mail;
	
	public Provider(Integer id,  String name, String location, String mail, int phone) {
		super();
		this.id = id;
		this.phone = phone;
		this.name = name;
		this.location = location;
		this.mail = mail;
	}
	
	public Provider(String name, String location, String mail, int phone) {
		super();
		this.phone = phone;
		this.name = name;
		this.location = location;
		this.mail = mail;
	}
	
	public Provider() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	@Override
	public String toString() {
		return "Provider [id=" + id + ", phone=" + phone + ", name=" + name + ", location=" + location + ", mail="
				+ mail + "]";
	}
	
	

}
