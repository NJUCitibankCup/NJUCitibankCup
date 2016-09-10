package nju.citicup.common.entity;

import nju.citicup.common.enumarate.OptionType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lenovo on 2016/9/7.
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name="BasicOptionInfo")
@DiscriminatorColumn(name="optionType",
        discriminatorType = DiscriminatorType.STRING,
        length=30)
@DiscriminatorValue("Eu")
public class BasicOptionInfo {

    /*期权标的*/
    String target;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /*ID*/
    int id;

    /*行权价格*/
    double executivePrice;

    /*成交日期*/
    Date tradeDate;

    /*期权价格*/
    double price;

    @Transient
    /*期权风险估计量*/
    double delta;

    @Transient
    /*期权gamma*/
    double gamma;

    protected BasicOptionInfo(){

    }

    public BasicOptionInfo(String target, double executivePrice, Date tradeDate) {
        this.target = target;
        this.executivePrice = executivePrice;
        this.tradeDate = tradeDate;
    }

    public BasicOptionInfo(String target, double executivePrice, Date tradeDate, double price) {
        this.target = target;
        this.executivePrice = executivePrice;
        this.tradeDate = tradeDate;
        this.price = price;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getExecutivePrice() {
        return executivePrice;
    }

    public void setExecutivePrice(double executivePrice) {
        this.executivePrice = executivePrice;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    @Override
    public String toString() {
        return "BasicOptionInfo{" +
                "target='" + target + '\'' +
                ", id=" + id +
                ", executivePrice=" + executivePrice +
                ", tradeDate=" + tradeDate +
                ", price=" + price +
                ", delta=" + delta +
                ", gamma=" + gamma +
                '}';
    }
}
