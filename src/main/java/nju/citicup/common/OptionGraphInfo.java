package nju.citicup.common;

/**
 * Created by lenovo on 2016/9/10.
 */
public class OptionGraphInfo {
    double St;
    double K;
    String startDate;
    String endDate;
    double sigmma;

    public OptionGraphInfo(double st, double k, String startDate, String endDate, double sigmma) {
        St = st;
        K = k;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sigmma = sigmma;
    }

    public double getSt() {
        return St;
    }

    public void setSt(double st) {
        St = st;
    }

    public double getK() {
        return K;
    }

    public void setK(double k) {
        K = k;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getSigmma() {
        return sigmma;
    }

    public void setSigmma(double sigmma) {
        this.sigmma = sigmma;
    }
}
