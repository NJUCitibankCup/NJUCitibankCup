package nju.citicup.common.vo;

/**
 * Created by nians on 2016/9/13.
 */
public class GraphVO {
    private double[] x_data;
    private double[] y_data;

    public GraphVO() {
    }

    public GraphVO(double[] x_data, double[] y_data) {
        this.x_data = x_data;
        this.y_data = y_data;
    }

    public double[] getX_data() {
        return x_data;
    }

    public void setX_data(double[] x_data) {
        this.x_data = x_data;
    }

    public double[] getY_data() {
        return y_data;
    }

    public void setY_data(double[] y_data) {
        this.y_data = y_data;
    }
}
