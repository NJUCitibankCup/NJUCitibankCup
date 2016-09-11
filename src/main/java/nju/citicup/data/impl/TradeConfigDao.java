package nju.citicup.data.impl;

import nju.citicup.common.entity.BasicFutureInfo;
import nju.citicup.common.entity.BasicTradeInfo;
import nju.citicup.data.dao.FutureDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

/**
 * Created by lenovo on 2016/9/11.
 */
public class TradeConfigDao {

    @Autowired
    FutureDao futureDao;

    @Transactional
    public void insertTradeInfo(BasicTradeInfo basicTradeInfo){
        BasicFutureInfo basicFutureInfo = futureDao.findOne("M1611");
        basicFutureInfo.addTradeInfo(basicTradeInfo);
    }
}
