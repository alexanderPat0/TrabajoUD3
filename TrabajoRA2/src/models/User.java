package models;

public class User {

	private int age;
	private Integer id;
	private String name, username, password;

	public User(Integer id, String name, int age, String username, String password) {
		super();
		this.id = id;
		this.age = age;
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public User(String name, int age, String username, String password) {
		super();
		this.age = age;
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", age=" + age + ", name=" + name + ", username=" + username + ", password="
				+ password + "]";
	}

}
