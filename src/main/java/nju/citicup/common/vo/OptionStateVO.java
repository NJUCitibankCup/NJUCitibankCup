package nju.citicup.common.vo;

import nju.citicup.common.enumarate.SafeType;

/**
 * Created by nians on 2016/9/12.
 */
public class OptionStateVO {
    private String futures_id;
    private String futures_name;
    private int number;
    private double delta;
    private SafeType safe;

    public OptionStateVO() {

    }

    public OptionStateVO(String futures_id, String futures_name, int number, double delta, SafeType safe) {
        this.futures_id = futures_id;
        this.futures_name = futures_name;
        this.number = number;
        this.delta = delta;
        this.safe = safe;
    }

    public String getFutures_id() {
        return futures_id;
    }

    public void setFutures_id(String futures_id) {
        this.futures_id = futures_id;
    }

    public String getFutures_name() {
        return futures_name;
    }

    public void setFutures_name(String futures_name) {
        this.futures_name = futures_name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public SafeType getSafe() {
        return safe;
    }

    public void setSafe(SafeType safe) {
        this.safe = safe;
    }
}
