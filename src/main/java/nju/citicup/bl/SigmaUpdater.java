package nju.citicup.bl;

import nju.citicup.data.dao.configdao.FutureConfigDao;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by admin on 2016/9/12.
 */
@Component
public class SigmaUpdater {
    Logger logger = Logger.getLogger(SigmaUpdater.class);

    @Autowired
    FutureConfigDao futureDao;

    //礼拜一 至 礼拜五 每天23:00触发
    @Scheduled(cron = "0 0 23 ? * MON-FRI")
    public void updateSigma(){
        logger.info("触发sigma每日更新");
        List<String> targetList = futureDao.findAllTarget();
        targetList.stream().forEach(target -> updateSigma(target));
        logger.info("结束sigma每日更新");
    }

    public void updateSigma(String target){
        futureDao.dailyUpdateSigmma(target);
    }

}
