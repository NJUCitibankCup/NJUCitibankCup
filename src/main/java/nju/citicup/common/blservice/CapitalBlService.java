package nju.citicup.common.blservice;

import nju.citicup.common.vo.ObjectDataWrapper;

/**
 * Created by lenovo on 2016/9/13.
 */
public interface CapitalBlService {
    ObjectDataWrapper getCapital(String stockCode);

    ObjectDataWrapper getVarGammaGraph(String[] option_list);

    ObjectDataWrapper getPredictResult(int lower_gamma, String [] option_list, String target);
}
