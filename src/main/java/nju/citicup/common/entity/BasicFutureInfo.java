package nju.citicup.common.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by lenovo on 2016/9/7.
 */
@Entity
@Table(name="BasicFutureInfo")
public class BasicFutureInfo {

    /*期货持仓量*/
    int quantity;

    @Id
    /*期货标的*/
    String target;

    /*期货delta*/
    double delta;

    /*某种期权标的的价格波动率*/
    double sigma;

    protected BasicFutureInfo() {
    }

    public BasicFutureInfo(String target, int quantity){
        this.target = target;
        this.quantity = quantity;
        this.delta = 1.0;
        this.sigma = 0;
    }


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
