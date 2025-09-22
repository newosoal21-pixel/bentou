package model;

import java.io.Serializable;

public class Product implements Serializable {
	private int id;
	private String name;
	private int price;
	private String imagePath;
	private int cal;
	
	 public Product(int id, String name, int price) {
	        this.id = id;
	        this.name = name;
	        this.price = price;
	    }

	
	public Product(int id, String name, int price, int cal, String imagePath) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.cal = cal;
		this.imagePath = imagePath;
	}
	public Product(int id, String name, int price, String imagePath) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.imagePath = imagePath;
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
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public int getCal() {
		return cal;
	}


	public void setCal(int cal) {
		this.cal = cal;
	}
	

}
