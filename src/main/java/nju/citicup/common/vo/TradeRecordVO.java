package nju.citicup.common.vo;

/**
 * Created by nians on 2016/9/13.
 */
public class TradeRecordVO {
    private String date;
    private double price;
    private int quantity;

    public TradeRecordVO() {
    }

    public TradeRecordVO(String date, double price, int quantity) {
        this.date = date;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
