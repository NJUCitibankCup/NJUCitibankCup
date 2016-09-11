package nju.citicup.common.jsonobj;

/**
 * Created by lenovo on 2016/9/10.
 */
public class OptionExtraInfo {

    double price;

    double delta;

    double gamma;

    public OptionExtraInfo(double price, double delta, double gamma) {
        this.price = price;
        this.delta = delta;
        this.gamma = gamma;
    }

    public OptionExtraInfo() {
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
}
