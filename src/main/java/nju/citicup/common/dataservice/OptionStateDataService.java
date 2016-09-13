package nju.citicup.common.dataservice;

import nju.citicup.common.entity.BasicOptionInfo;
import nju.citicup.common.enumarate.SafeType;

import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/9/13.
 */
public interface OptionStateDataService {

    Map<String, List<BasicOptionInfo>> getTotalOptionList();

    SafeType getOptionSafety(String target, List<BasicOptionInfo> basicOptionInfoList);

    double getFutureTotalDelta(String target);
}
