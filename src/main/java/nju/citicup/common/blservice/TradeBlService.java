package nju.citicup.common.blservice;

import nju.citicup.common.vo.ObjectDataWrapper;

/**
 * Created by lenovo on 2016/9/14.
 */
public interface TradeBlService {
    ObjectDataWrapper getHistory(String future_id);
}
