package nju.citicup.common.dataservice;

import nju.citicup.common.entity.BasicOptionInfo;

import java.util.List;

/**
 * Created by lenovo on 2016/9/13.
 */
public interface CapitalDataService {
    List<BasicOptionInfo> getOptionListByFutureId(String stockCode);
}
