package nju.citicup.common.entity;

import nju.citicup.common.enumarate.OptionType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lenovo on 2016/9/7.
 */
@Entity
@Table(name = "BasicOptionInfo")
public class BasicOptionInfo {

    /*期权标的*/
    String target;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    /*ID*/
    int id;

    /*期权种类*/
    OptionType optionType;

    /*行权价格*/
    double executivePrice;

    /*成交日期*/
    Date tradeDate;

    /*障碍系数*/
    double H;

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

    public BasicOptionInfo(String target, OptionType optionType, double executivePrice, Date tradeDate, double h) {
        this.target = target;
        this.optionType = optionType;
        this.executivePrice = executivePrice;
        this.tradeDate = tradeDate;
        H = h;
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

    public OptionType getOptionType() {
        return optionType;
    }

    public void setOptionType(OptionType optionType) {
        this.optionType = optionType;
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

    public double getH() {
        return H;
    }

    public void setH(double h) {
        H = h;
    }
}
