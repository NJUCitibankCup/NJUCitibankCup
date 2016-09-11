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

    @ManyToOne
    @JoinColumn(name = "target")
    BasicFutureInfo basicFutureInfo;

    @Transient
    /*期权风险估计量*/
    double delta;

    @Transient
    /*期权gamma*/
    double gamma;

    protected BasicOptionInfo(){

    }

    public BasicOptionInfo(double executivePrice, Date tradeDate) {
        this.executivePrice = executivePrice;
        this.tradeDate = tradeDate;
    }

    public BasicOptionInfo(double executivePrice, Date tradeDate, double price) {
        this.executivePrice = executivePrice;
        this.tradeDate = tradeDate;
        this.price = price;
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

    public BasicFutureInfo getBasicFutureInfo() {
        return basicFutureInfo;
    }

    public void setBasicFutureInfo(BasicFutureInfo basicFutureInfo) {
        this.basicFutureInfo = basicFutureInfo;
    }

    @Override
    public String toString() {
        return "BasicOptionInfo{" +
                '\'' +
                ", id=" + id +
                ", executivePrice=" + executivePrice +
                ", tradeDate=" + tradeDate +
                ", price=" + price +
                ", delta=" + delta +
                ", gamma=" + gamma +
                '}';
    }
}
