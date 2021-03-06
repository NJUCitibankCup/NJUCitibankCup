package nju.citicup.data.dao.configdao;

import nju.citicup.common.entity.BasicFutureInfo;
import nju.citicup.data.dao.baiscdao.FutureDao;
import nju.citicup.data.future.FutureInfoClient;
import nju.citicup.data.pyalgo.PyAlgoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lenovo on 2016/9/11.
 */
@Component
public class FutureConfigDao {

    @Autowired
    FutureDao futureDao;

    @Autowired
    PyAlgoClient pyAlgoClient;

    @Autowired
    FutureInfoClient futureInfoClient;

    public void dailyUpdateSigmma(String target){
        double updatedSigmma = pyAlgoClient.caculateSigma(target);
        futureDao.setSigma(target, updatedSigmma);
    }

    public void updateFutureQuantity(String target, int quantity){ futureDao.setQuantity(quantity, target);}

    public List<String> findAllTarget(){
        return futureDao.findAllTarget();
    }

    public List<BasicFutureInfo> getAllBasicFutureInfo(){
        return (List<BasicFutureInfo>) futureDao.findAll();
    }

    public BasicFutureInfo getFutureByTarget(String target){
        return futureDao.findOne(target);
    }

    public List<String> findTargetByType(String type){
        return futureDao.findByTargetStartsWith(type)
                .stream()
                .map(BasicFutureInfo::getTarget)
                .filter(f -> f.length() == type.length()+4)
                .collect(Collectors.toList());
    }
}
