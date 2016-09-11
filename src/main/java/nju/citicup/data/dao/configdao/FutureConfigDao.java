package nju.citicup.data.dao.configdao;

import nju.citicup.data.dao.baiscdao.FutureDao;
import nju.citicup.data.pyalgo.PyAlgoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 2016/9/11.
 */
@Component
public class FutureConfigDao {

    @Autowired
    FutureDao futureDao;

    @Autowired
    PyAlgoClient pyAlgoClient;


    public void dailyUpdateSigmma(String target){
        double updatedSigmma = pyAlgoClient.caculateSigma(target);
        futureDao.setSigma(target, updatedSigmma);
    }
}
