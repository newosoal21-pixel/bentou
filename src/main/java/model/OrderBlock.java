package model;

import java.io.Serializable;
import java.util.List;

// 注文全体（orders_idごと）を保持するブロック
public class OrderBlock implements Serializable {
    private int orderId;  
    private List<OrderItem> items;   // この注文に含まれる品目リスト

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}