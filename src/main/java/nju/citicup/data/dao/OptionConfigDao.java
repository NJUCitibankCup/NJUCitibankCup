package nju.citicup.data.dao;

import nju.citicup.common.OptionExtraInfo;
import nju.citicup.common.entity.BasicFutureInfo;
import nju.citicup.common.entity.BasicOptionInfo;
import nju.citicup.common.entity.BasicTradeInfo;
import nju.citicup.data.pyalgo.PyAlgoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/9/10.
 */
@Component
public class OptionConfigDao {

    @Autowired
    OptionDao optionDao;

    @Autowired
    FutureDao futureDao;

    @Autowired
    PyAlgoClient pyAlgoClient;

    @PersistenceContext
    EntityManager entityManager;

    /**
     * 将期权信息(包括最终价格)确定后录入数据库中
     * @param basicOptionInfo
     */
    public void insertOption(BasicOptionInfo basicOptionInfo){
        OptionExtraInfo optionExtraInfo = pyAlgoClient.getOptionInfo(basicOptionInfo);
        basicOptionInfo.setPrice(optionExtraInfo.getPrice());
        optionDao.save(basicOptionInfo);
    }

    /**
     * 根据某种期权标的获取对应的期权列表(已经计算好delta和gamma)
     * @param target
     * @return
     */
    public List<BasicOptionInfo> getOptionByTarget(String target){
        List<BasicOptionInfo> tempList = optionDao.findByTarget(target);
        List<BasicOptionInfo> resultList = new ArrayList<>();

        for(BasicOptionInfo basicOptionInfo: tempList){
            resultList.add(getOptionById(basicOptionInfo.getId()));
        }
        return resultList;
    }

    /**
     * 根据ID来找到期权的信息(一般是界面将ID传回来时使用这个)
     * @param id
     * @return
     */
    public BasicOptionInfo getOptionById(int id){

        BasicOptionInfo basicOptionInfo = optionDao.findOne(id);
        OptionExtraInfo optionExtraInfo = pyAlgoClient.getOptionInfo(basicOptionInfo);
        basicOptionInfo.setGamma(optionExtraInfo.getGamma());
        basicOptionInfo.setDelta(optionExtraInfo.getDelta());

        return basicOptionInfo;
    }

    @Transactional
    public void insertTradeInfo(BasicTradeInfo basicTradeInfo){
//            BasicFutureInfo basicFutureInfo = futureDao.findOne(basicTradeInfo.getTarget());
//            basicTradeInfo.setBasicFutureInfo(basicFutureInfo);
            entityManager.persist(basicTradeInfo);
    }
}
