package model;

import java.io.Serializable;

public class OrderHistoryBean implements Serializable {
	private int orders_id;
	private String itemname;
	private int price;
	private String image;
	private int quantity;
	
	public OrderHistoryBean(int int1, String string, int int2, String string2, int int3) {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public int getOrders_id() {
		return orders_id;
	}
	public void setOrders_id(int orders_id) {
		this.orders_id = orders_id;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
