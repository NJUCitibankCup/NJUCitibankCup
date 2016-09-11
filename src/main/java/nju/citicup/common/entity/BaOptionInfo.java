package nju.citicup.common.entity;

import nju.citicup.common.enumarate.OptionType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by lenovo on 2016/9/10.
 */
@Entity
@DiscriminatorValue("Ba")
public class BaOptionInfo extends BasicOptionInfo {
    double H;

    public BaOptionInfo(){

    }

    public BaOptionInfo(double executivePrice, Date tradeDate, double h) {
        super(executivePrice, tradeDate);
        H = h;
    }

    public BaOptionInfo(double executivePrice, Date tradeDate, double h, double price) {
        super(executivePrice, tradeDate, price);
        H = h;
    }

    public double getH() {
        return H;
    }

    public void setH(double h) {
        H = h;
    }

    @Override
    public String toString() {
        return "BaOptionInfo{" +
                "H=" + H +
                "} " + super.toString();
    }
}
