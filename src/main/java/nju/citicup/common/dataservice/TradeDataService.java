package nju.citicup.common.dataservice;

import nju.citicup.common.entity.BasicTradeInfo;

import java.util.List;

/**
 * Created by lenovo on 2016/9/14.
 */
public interface TradeDataService {
    List<BasicTradeInfo> getTradeByTarget(String target);
}
