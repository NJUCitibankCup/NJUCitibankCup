package nju.citicup.bl.ScheduleTasks;

import nju.citicup.data.future.FutureInfoClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by admin on 2016/9/14.
 */
@Component
public class FutureCacheClear {

    Logger logger = Logger.getLogger(SigmaUpdater.class);

    @Autowired
    FutureInfoClient client;

    @Scheduled(cron = "0 0 23 ? * MON-FRI")
    public void clearFutureCache(){
        logger.info("清空future缓存");
        client.clearCache();
        logger.info("清空完毕");
    }

}



