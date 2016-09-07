package nju.citicup.common;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by lenovo on 2016/9/7.
 */
@Entity
public class BasicTradeInfo {

    /*调仓成本*/
    double cost;

    /*调仓期货标的*/
    String target;

    /*调仓日期*/
    Date tradeDate;

    /*调仓数量*/
    int count;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
