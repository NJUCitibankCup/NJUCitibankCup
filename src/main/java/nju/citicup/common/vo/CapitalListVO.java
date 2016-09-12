package nju.citicup.common.vo;

import nju.citicup.common.enumarate.SafeType;

import java.util.List;

/**
 * Created by nians on 2016/9/13.
 */
public class CapitalListVO {
    private double cost;
    private double delta;
    private SafeType safe;
    private List<CapitalVO> data;

    public CapitalListVO() {
    }

    public CapitalListVO(double cost, double delta, SafeType safe, List<CapitalVO> data) {
        this.cost = cost;
        this.delta = delta;
        this.safe = safe;
        this.data = data;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
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

    public List<CapitalVO> getData() {
        return data;
    }

    public void setData(List<CapitalVO> data) {
        this.data = data;
    }
}
