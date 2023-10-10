package models;

public class Transaction {

	private Integer id;
	private int id_prod;
	private float price;
	private int amount;
	
	public Transaction() {
		super();
	}
	
	public Transaction(Integer id, int id_prod, float price, int amount) {
		super();
		this.id = id;
		this.id_prod = id_prod;
		this.price = price;
		this.amount = amount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_prod() {
		return id_prod;
	}

	public void setId_prod(int id_prod) {
		this.id_prod = id_prod;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", id_prod=" + id_prod + ", price=" + price + ", amount=" + amount + "]";
	}
	
}
