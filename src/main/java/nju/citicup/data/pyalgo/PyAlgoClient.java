package nju.citicup.data.pyalgo;

import nju.citicup.common.BasicOptionInfo;
import nju.citicup.common.util.Target2Date;
import nju.citicup.data.future.FutureInfoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lenovo on 2016/9/7.
 */
@Component
public class PyAlgoClient {

    @Autowired
    FutureInfoClient futureInfoClient;

    @Value("${citialgo.server.address}")
    private String serverAddress;

    public void getEuOptionInfo(BasicOptionInfo basicOptionInfo){
        RestTemplate restTemplate = new RestTemplate();

        String uri = serverAddress+"Eu?St={St}&startDate={startDate}" +
                "&endDate={endDate}&K={K}&sigma={sigma}";

        Map<String, Object> varList = new TreeMap<String, Object>();

        varList.put("St", getFuturePrice(basicOptionInfo.getTarget()));
        varList.put("startDate", basicOptionInfo.getTradeDate());
        varList.put("endDate", Target2Date.target2Date(basicOptionInfo.getTarget()));
        varList.put("K", basicOptionInfo.getExecutivePrice());
        varList.put("sigma", 0.02);

        String result = restTemplate.getForObject(uri, String.class, varList);
        System.out.println(result);

    }

    private double getFuturePrice(String target){
        return 0.0;
    }
}
