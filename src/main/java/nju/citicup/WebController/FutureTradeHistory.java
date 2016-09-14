package nju.citicup.WebController;

import nju.citicup.common.blservice.TradeBlService;
import nju.citicup.common.vo.ObjectDataWrapper;
import nju.citicup.common.vo.TradeRecordListVO;
import nju.citicup.common.vo.TradeRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

/**
 * Created by nians on 2016/9/13.
 */
@Controller
public class FutureTradeHistory {

    @Autowired
    TradeBlService tradeBlService;

    @RequestMapping("/api/getHistory")
    @ResponseBody
    public ObjectDataWrapper getHistory(@RequestParam("future_id") String future_id) {
        return tradeBlService.getHistory(future_id);
    }
}
