package model;

import java.io.Serializable;

public class Product implements Serializable {
	private int id;
	private String name;
	private int price;
	
	public Product(int id, String name, int price, String string) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return  id + "," + name + "," + price;
	}
	

}
