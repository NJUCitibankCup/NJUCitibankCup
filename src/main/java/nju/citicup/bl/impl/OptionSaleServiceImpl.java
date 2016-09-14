package nju.citicup.bl.impl;

import nju.citicup.bl.FutureNameMapper;
import nju.citicup.common.blservice.OptionSaleService;
import nju.citicup.common.entity.BaOptionInfo;
import nju.citicup.common.entity.BasicOptionInfo;
import nju.citicup.common.enumarate.OptionType;
import nju.citicup.common.util.DateUtil;
import nju.citicup.common.vo.FutureIdMapEntry;
import nju.citicup.data.dao.configdao.FutureConfigDao;
import nju.citicup.data.dao.configdao.OptionConfigDao;
import nju.citicup.data.future.FutureInfoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 2016/9/13.
 */
@Service
public class OptionSaleServiceImpl implements OptionSaleService {

    @Autowired
    FutureNameMapper mapper;

    @Autowired
    FutureConfigDao futureConfigDao;

    @Autowired
    OptionConfigDao optionConfigDao;

    @Autowired
    FutureInfoClient futureInfoClient;

    public List<String> getFutureType(){
        return mapper.getMapper().values()
                .stream()
                .collect(Collectors.toList());
    }

    public List<FutureIdMapEntry> getEntryByType(String type){
        type = mapper.convert(type);
        List<String> targetList = futureConfigDao.findTargetByType(type);
        return targetList.stream()
                .map(s -> new FutureIdMapEntry(s,mapper.convert(s)))
                .collect(Collectors.toList());
    }

    @Override
    public double getOptionPrice
            (String futuresId, OptionType type, double executivePrice, Integer H) {
        BasicOptionInfo option = createOption(type, executivePrice, H);
        return optionConfigDao.getOptionPrice(option,futuresId);
    }

    @Override
    public void sellOption
            (String futuresId, OptionType type, double executivePrice, Integer H) {
        BasicOptionInfo option = createOption(type, executivePrice, H);
        optionConfigDao.insertOneOption(option,futuresId);
    }

    private BasicOptionInfo createOption(OptionType type, double executivePrice, Integer H){
        BasicOptionInfo option = null;
        Date startDate = DateUtil.getCurrentDate();
        switch (type){
            case Eu:
                option = new BasicOptionInfo(executivePrice,startDate);
                break;
            case Ba:
                option = new BaOptionInfo(executivePrice,startDate,H);
                break;
        }
        return  option;
    }

}
