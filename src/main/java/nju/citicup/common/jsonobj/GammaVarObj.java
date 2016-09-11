package nju.citicup.common.jsonobj;

import java.util.List;

/**
 * Created by lenovo on 2016/9/11.
 */
public class GammaVarObj {
    List<Integer> lowerGammaList;

    List<Double> varList;

    public GammaVarObj() {
    }

    public GammaVarObj(List<Integer> lowerGammaList, List<Double> varList) {
        this.lowerGammaList = lowerGammaList;
        this.varList = varList;
    }

    public List<Integer> getLowerGammaList() {
        return lowerGammaList;
    }

    public void setLowerGammaList(List<Integer> lowerGammaList) {
        this.lowerGammaList = lowerGammaList;
    }

    public List<Double> getVarList() {
        return varList;
    }

    public void setVarList(List<Double> varList) {
        this.varList = varList;
    }
}
