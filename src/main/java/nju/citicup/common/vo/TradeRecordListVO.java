package nju.citicup.common.vo;

import java.util.List;

/**
 * Created by nians on 2016/9/13.
 */
public class TradeRecordListVO {
    private int quantity;
    private int average_price;
    private List<TradeRecordVO> data;

    public TradeRecordListVO() {
    }

    public TradeRecordListVO(int quantity, int average_price, List<TradeRecordVO> data) {
        this.quantity = quantity;
        this.average_price = average_price;
        this.data = data;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAverage_price() {
        return average_price;
    }

    public void setAverage_price(int average_price) {
        this.average_price = average_price;
    }

    public List<TradeRecordVO> getData() {
        return data;
    }

    public void setData(List<TradeRecordVO> data) {
        this.data = data;
    }
}
