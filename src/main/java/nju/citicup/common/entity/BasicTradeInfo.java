package nju.citicup.common.entity;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.util.Date;

/**
 * Created by lenovo on 2016/9/7.
 */
@Entity
@Table(name = "BasicTradeInfo")
public class BasicTradeInfo {

    /*调仓成本*/
    double cost;

    /*调仓期货标的*/
    String targetId;

    /*调仓日期*/
    Date tradeDate;

    /*调仓数量*/
    int count;

    @ManyToOne
    @JoinColumn(name = "target")
    BasicFutureInfo basicFutureInfo;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    protected BasicTradeInfo() {
    }

    public BasicTradeInfo(double cost, String targetId, Date tradeDate, int count) {
        this.cost = cost;
        this.targetId = targetId;
        this.tradeDate = tradeDate;
        this.count = count;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
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


    public BasicFutureInfo getBasicFutureInfo() {
        return basicFutureInfo;
    }

    public void setBasicFutureInfo(BasicFutureInfo basicFutureInfo) {
        this.basicFutureInfo = basicFutureInfo;
    }
}
