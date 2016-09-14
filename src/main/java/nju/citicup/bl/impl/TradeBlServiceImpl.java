package nju.citicup.bl.impl;

import nju.citicup.common.blservice.TradeBlService;
import nju.citicup.common.dataservice.TradeDataService;
import nju.citicup.common.entity.BasicTradeInfo;
import nju.citicup.common.util.DateUtil;
import nju.citicup.common.vo.ObjectDataWrapper;
import nju.citicup.common.vo.TradeRecordListVO;
import nju.citicup.common.vo.TradeRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/9/14.
 */
@Component
public class TradeBlServiceImpl implements TradeBlService{

    @Autowired
    TradeDataService tradeDataService;

    @Override
    public ObjectDataWrapper getHistory(String future_id) {

        ObjectDataWrapper wrapper = new ObjectDataWrapper();

        List<BasicTradeInfo> tradeInfos = tradeDataService.getTradeByTarget(future_id);

        List<TradeRecordVO> tradeRecordVOs = new ArrayList<>();
        int quantity = 0;
        double total_cost = 0;

        for(BasicTradeInfo basicTradeInfo: tradeInfos){
            quantity += basicTradeInfo.getCount();
            total_cost += basicTradeInfo.getCost();
            tradeRecordVOs.add(new TradeRecordVO(DateUtil.normalizeDate(basicTradeInfo.getTradeDate()),
                    basicTradeInfo.getCost(), basicTradeInfo.getCount()));
        }

        double avg_price = total_cost / (double) tradeRecordVOs.size();

        TradeRecordListVO tradeRecordListVO = new TradeRecordListVO(quantity, avg_price, tradeRecordVOs);

        wrapper.setCondition("success");
        wrapper.setMsg("");
        wrapper.setData(tradeRecordListVO);

        return wrapper;
    }
}
