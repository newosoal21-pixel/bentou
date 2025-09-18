package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class EmployeeOrder implements Serializable {
    private LocalDateTime dateTime;     // 注文日時
    private String itemName;            // 弁当名
    private int totalQuantity;          // 注文個数総計
    private int totalPrice;             // 商品毎注文金額総計

    public EmployeeOrder(LocalDateTime dateTime, String itemName, int totalQuantity, int totalPrice) {
        this.dateTime = dateTime;
        this.itemName = itemName;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}