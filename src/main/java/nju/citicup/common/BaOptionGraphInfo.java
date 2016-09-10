package nju.citicup.common;

/**
 * Created by lenovo on 2016/9/10.
 */
public class BaOptionGraphInfo extends OptionGraphInfo {
    double H;

    public BaOptionGraphInfo(double st, double k, String startDate, String endDate, double sigmma, double h) {
        super(st, k, startDate, endDate, sigmma);
        H = h;
    }

    public double getH() {
        return H;
    }

    public void setH(double h) {
        H = h;
    }
}
