package nju.citicup.common.vo;

import nju.citicup.common.enumarate.OptionType;

/**
 * Created by nians on 2016/9/13.
 */
public class CapitalVO {
    private String option_id;
    private OptionType type;
    private String due_date;
    private double delta;
    private double sell_price;
    private double futures_price;
    private double cost;

    public CapitalVO() {
    }

    public CapitalVO(String option_id, OptionType type, String due_date, double delta, double sell_price, double futures_price, double cost) {
        this.option_id = option_id;
        this.type = type;
        this.due_date = due_date;
        this.delta = delta;
        this.sell_price = sell_price;
        this.futures_price = futures_price;
        this.cost = cost;
    }

    public String getOption_id() {
        return option_id;
    }

    public void setOption_id(String option_id) {
        this.option_id = option_id;
    }

    public OptionType getType() {
        return type;
    }

    public void setType(OptionType type) {
        this.type = type;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public double getSell_price() {
        return sell_price;
    }

    public void setSell_price(double sell_price) {
        this.sell_price = sell_price;
    }

    public double getFutures_price() {
        return futures_price;
    }

    public void setFutures_price(double futures_price) {
        this.futures_price = futures_price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
