package nju.citicup.bl.impl;

import nju.citicup.bl.FutureNameMapper;
import nju.citicup.common.blservice.OptionStateBlService;
import nju.citicup.common.dataservice.OptionStateDataService;
import nju.citicup.common.entity.BasicOptionInfo;
import nju.citicup.common.enumarate.SafeType;
import nju.citicup.common.vo.ListDataWrapper;
import nju.citicup.common.vo.OptionStateListVO;
import nju.citicup.common.vo.OptionStateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by lenovo on 2016/9/13.
 */
@Component
public class OptionStateBlServiceImpl implements OptionStateBlService{

    @Autowired
    OptionStateDataService optionStateDataService;

    @Autowired
    FutureNameMapper futureNameMapper;

    @Override
    public ListDataWrapper getOptionStateList() {

        ListDataWrapper wrapper = new ListDataWrapper();
        wrapper.setMsg("");
        wrapper.setCondition("success");

        Map<String, List<BasicOptionInfo>> optionMap = optionStateDataService.getTotalOptionList();
        Map<String, List<OptionStateVO>> optionStateVOMap = new TreeMap<>();


        List<Object> optionStateListVOs = new ArrayList<>();

        for(Map.Entry<String, List<BasicOptionInfo>> entry: optionMap.entrySet()){
            String target = entry.getKey();
            List<BasicOptionInfo> optionInfoList = entry.getValue();

            double delta = optionStateDataService.getFutureTotalDelta(target);
            SafeType type = optionStateDataService.getOptionSafety(target, optionInfoList);

            for(BasicOptionInfo basicOptionInfo: optionInfoList)
                delta += basicOptionInfo.getDelta();

            OptionStateVO optionStateVO = new OptionStateVO(target, futureNameMapper.convert(target),
                    optionInfoList.size(), delta, type);

            String futureType = futureNameMapper.cut(target);

            if(optionStateVOMap.containsKey(futureType)){
                List<OptionStateVO> optionStateVOList = optionStateVOMap.get(futureType);
                optionStateVOList.add(optionStateVO);
                optionStateVOMap.put(futureType, optionStateVOList);
            }else{
                List<OptionStateVO> optionStateVOList = Arrays.asList(optionStateVO);
                optionStateVOMap.put(futureType, optionStateVOList);
            }

        }

        for(Map.Entry<String, List<OptionStateVO>> entry: optionStateVOMap.entrySet()){
            String type_eng = entry.getKey();
            List<OptionStateVO> optionStateVOList = entry.getValue();

            String type = futureNameMapper.convert(type_eng);
            optionStateListVOs.add(new OptionStateListVO(type, optionStateVOList));
        }

        wrapper.setData(optionStateListVOs);

        return wrapper;
    }
}
