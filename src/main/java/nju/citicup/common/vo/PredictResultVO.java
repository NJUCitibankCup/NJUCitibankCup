package nju.citicup.common.vo;

import nju.citicup.common.enumarate.SafeType;

/**
 * Created by nians on 2016/9/13.
 */
public class PredictResultVO {
    private String futures_id;
    private String futures_name;
    private int number;
    private double origin_cost;
    private int origin_number;
    private double origin_delta;
    private double current_delta;
    private int current_number;
    private double var;
    private SafeType safe;

    public PredictResultVO() {
    }

    public PredictResultVO(String futures_id, String futures_name, int number, double origin_cost, int origin_number, double origin_delta, double current_delta, int current_number, double var, SafeType safe) {
        this.futures_id = futures_id;
        this.futures_name = futures_name;
        this.number = number;
        this.origin_cost = origin_cost;
        this.origin_number = origin_number;
        this.origin_delta = origin_delta;
        this.current_delta = current_delta;
        this.current_number = current_number;
        this.var = var;
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

    public double getOrigin_cost() {
        return origin_cost;
    }

    public void setOrigin_cost(double origin_cost) {
        this.origin_cost = origin_cost;
    }

    public int getOrigin_number() {
        return origin_number;
    }

    public void setOrigin_number(int origin_number) {
        this.origin_number = origin_number;
    }

    public double getOrigin_delta() {
        return origin_delta;
    }

    public void setOrigin_delta(double origin_delta) {
        this.origin_delta = origin_delta;
    }

    public double getCurrent_delta() {
        return current_delta;
    }

    public void setCurrent_delta(double current_delta) {
        this.current_delta = current_delta;
    }

    public int getCurrent_number() {
        return current_number;
    }

    public void setCurrent_number(int current_number) {
        this.current_number = current_number;
    }

    public double getVar() {
        return var;
    }

    public void setVar(double var) {
        this.var = var;
    }

    public SafeType getSafe() {
        return safe;
    }

    public void setSafe(SafeType safe) {
        this.safe = safe;
    }
}
