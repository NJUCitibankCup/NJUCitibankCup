package nju.citicup.WebController;

import nju.citicup.common.vo.ObjectDataWrapper;
import nju.citicup.common.vo.TradeRecordListVO;
import nju.citicup.common.vo.TradeRecordVO;
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
    @RequestMapping("/api/getHistory")
    @ResponseBody
    public ObjectDataWrapper getHistory(@RequestParam("future_id") String future_id) {
        ObjectDataWrapper wrapper = new ObjectDataWrapper();
        wrapper.setMsg("");
        wrapper.setCondition("success");

        TradeRecordListVO vo = new TradeRecordListVO(150, 8, Arrays.asList(new TradeRecordVO("2016-01-01", 1, 10), new TradeRecordVO("2016-01-02", 2, 30)));

        wrapper.setData(vo);
        return wrapper;
    }
}
