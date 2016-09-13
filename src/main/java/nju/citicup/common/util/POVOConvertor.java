package nju.citicup.common.util;

import nju.citicup.bl.FutureNameMapper;
import nju.citicup.common.entity.BaOptionInfo;
import nju.citicup.common.entity.BasicOptionInfo;
import nju.citicup.common.enumarate.OptionType;
import nju.citicup.common.enumarate.SafeType;
import nju.citicup.common.vo.CapitalVO;
import nju.citicup.common.vo.OptionStateVO;
import nju.citicup.data.pyalgo.PyAlgoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by lenovo on 2016/9/13.
 */
@Component
public class POVOConvertor {

    @Autowired
    PyAlgoClient pyAlgoClient;

    @Autowired
    FutureNameMapper mapper;


    public CapitalVO convertOptionInfo2Capital(BasicOptionInfo basicOptionInfo, String stockCode){
        double St = pyAlgoClient.caculateSigma(stockCode);
        String option_id = basicOptionInfo.getId()+"";
        OptionType type = OptionType.Eu;
        if(basicOptionInfo instanceof BaOptionInfo)
            type = OptionType.Ba;
        String due_date = DateUtil.target2Date(stockCode);

        return new CapitalVO(option_id, type, due_date, basicOptionInfo.getDelta(),
                basicOptionInfo.getPrice(), St, basicOptionInfo.getCost());
    }

    public OptionStateVO convertOptionList2OptionState(List<BasicOptionInfo> basicOptionInfoList, String stockCode, SafeType safeType){
        String futures_name = mapper.convert(stockCode);
        String futures_id = stockCode;
        int number = basicOptionInfoList.size();
        double delta = 0;
        for(BasicOptionInfo optionInfo: basicOptionInfoList)
            delta += optionInfo.getDelta();

        return new OptionStateVO(futures_id, futures_name, number, delta, safeType);
    }
}
