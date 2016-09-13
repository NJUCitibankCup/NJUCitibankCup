package nju.citicup.bl.impl;

import nju.citicup.bl.FutureNameMapper;
import nju.citicup.bl.FutureTypeService;
import nju.citicup.common.vo.FutureIdMapEntry;
import nju.citicup.data.dao.configdao.FutureConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 2016/9/13.
 */
@Service
public class FutureTypeServiceImpl implements FutureTypeService {

    @Autowired
    FutureNameMapper mapper;

    @Autowired
    FutureConfigDao futureConfigDao;

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
}
