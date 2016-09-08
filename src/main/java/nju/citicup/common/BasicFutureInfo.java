package nju.citicup.common;

import javax.persistence.Entity;

/**
 * Created by lenovo on 2016/9/7.
 */
public class BasicFutureInfo {

    /*期货持仓量*/
    int quantity;

    /*期货标的*/
    String target;

    /*期货delta*/
    double delta;

    /*某种期权标的的价格波动率*/
    double sigma;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public double getSigma() {
        return sigma;
    }

    public void setSigma(double sigma) {
        this.sigma = sigma;
    }
}
