package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// 注文ブロック内の個別の品目
public class OrderItem implements Serializable {
    private LocalDateTime orderDateTime; // 注文日時
    private String itemName;                  // 商品名
    private int quantity;                         // 個数
    private int unitPrice;                        // 単価

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    // 合計金額
    public int getAmount() {
        return unitPrice * quantity;
    }
    
    // 日付＋時間のフォーマット
    public String getFormattedDate() {
        // 0000/00/00 00:00
        return orderDateTime.format(DateTimeFormatter.ofPattern("MM/dd HH:mm"));
    }
}