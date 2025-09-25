package model;

import java.io.Serializable;

public class AdminOrder implements Serializable {
    private String itemName;
    private int totalQuantity;
    private int totalPrice;
    private String departmentName;
    private int orderFlag; // 追加: 0 未発注, 1 発注済
    public AdminOrder() {}
    public AdminOrder(String itemName, int totalQuantity, int totalPrice, String departmentName) {
        this.itemName = itemName;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.departmentName = departmentName;
        this.orderFlag = 0;
    }

    // 既存の getter / setter
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public int getTotalQuantity() { return totalQuantity; }
    public void setTotalQuantity(int totalQuantity) { this.totalQuantity = totalQuantity; }

    public int getTotalPrice() { return totalPrice; }
    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    // --- 追加 ---
    public int getOrderFlag() { return orderFlag; }
    public void setOrderFlag(int orderFlag) { this.orderFlag = orderFlag; }

    @Override
    public String toString() {
        return "AdminOrder [itemName=" + itemName + ", totalQuantity=" + totalQuantity + ", totalPrice=" + totalPrice
                + ", departmentName=" + departmentName + ", orderFlag=" + orderFlag + "]";
    }
}