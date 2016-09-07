package nju.citicup.common;

import javax.persistence.Entity;

/**
 * Created by lenovo on 2016/9/7.
 */
@Entity
public class BasicFutureInfo {

    /*期货持仓量*/
    int quantity;

    /*期货标的*/
    String target;

    /*期货delta*/
    double delta;

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
}
