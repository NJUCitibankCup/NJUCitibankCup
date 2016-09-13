package nju.citicup.common.dataservice;

import nju.citicup.common.entity.BasicOptionInfo;
import nju.citicup.common.enumarate.SafeType;
import nju.citicup.common.jsonobj.GammaVarObj;

import java.util.List;

/**
 * Created by lenovo on 2016/9/13.
 */
public interface CapitalDataService {

    List<BasicOptionInfo> getOptionListByFutureId(String stockCode);

    SafeType judgeOptionSafety(String target, List<BasicOptionInfo> optionInfoList);

    GammaVarObj getGraphInfo(String[] option_list);
}
