package nju.citicup.data.pyalgo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import nju.citicup.common.OptionExtraInfo;
import nju.citicup.common.entity.BaOptionInfo;
import nju.citicup.common.entity.BasicFutureInfo;
import nju.citicup.common.entity.BasicOptionInfo;
import nju.citicup.common.enumarate.OptionType;
import nju.citicup.common.util.DateUtil;
import nju.citicup.data.dao.FutureDao;
import nju.citicup.data.dao.OptionDao;
import nju.citicup.data.future.FutureInfoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lenovo on 2016/9/7.
 */
@Component
public class PyAlgoClient {

    @Autowired
    FutureInfoClient futureInfoClient;

    @Autowired
    FutureDao futureDao;

    @Autowired
    OptionDao optionDao;

    @Value("${citialgo.server.address}")
    private String serverAddress;

    /**
     *
     * @param basicOptionInfo 基础期权信息
     */
    public OptionExtraInfo getOptionInfo(BasicOptionInfo basicOptionInfo){
        RestTemplate restTemplate = new RestTemplate();

        String uri = "";
        if(basicOptionInfo instanceof BaOptionInfo)
            uri = serverAddress+"Ba?St={St}&startDate={startDate}" +
                    "&endDate={endDate}&K={K}&sigmma={sigmma}&H={H}";

        else
             uri = serverAddress+"Eu?St={St}&startDate={startDate}" +
                "&endDate={endDate}&K={K}&sigmma={sigmma}";

        Map<String, Object> varList = getVarList(basicOptionInfo);

        String result = restTemplate.getForObject(uri, String.class, varList);
        System.out.println(result);

        ObjectMapper mapper = new ObjectMapper();
        OptionExtraInfo optionExtraInfo = null;
        try {
            optionExtraInfo = mapper.readValue(result, OptionExtraInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return optionExtraInfo;
    }

    /**
     *
     * @param target
     * @return 计算得出的某种期权标的的价格波动率
     */
    public String caculateSigma(String target){

        RestTemplate restTemplate = new RestTemplate();

        String url = serverAddress + "sigmma";

        List<Double> priceList = getFutureHistoryPrice(target);

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";

        try {
            jsonStr = mapper.writeValueAsString(priceList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("priceList", (List<String>) Arrays.asList(jsonStr) );

        String result = restTemplate.postForObject(url, map, String.class);
        double convertResult = Double.parseDouble(result);
        System.out.println(futureDao.setSigma(target, convertResult));


        return result;
    }

    /**
     *
     * @param lowerGamma 用户选定的一个gamma
     * @param optionList 用户选定的期权列表
     * @param target 期权标的
     */
    public void hedgeCriteria(double lowerGamma, List<BasicOptionInfo> optionList, String target){
        RestTemplate restTemplate = new RestTemplate();

        String url = serverAddress + "hedge?number={number}&totalDelta={totalDelta}" +
                "&lowerGamma={lowerGamma}&upperGamma={upperGamma}&St={St}" +
                "&startDate={startDate}&endDate={endDate}";

        Map<String, Object> varList = new TreeMap<String, Object>();

        BasicFutureInfo basicFutureInfo = futureDao.findOne(target);
        int number = basicFutureInfo.getQuantity();

        double totalDelta = 0;
        double totalGamma = 0;

        for(BasicOptionInfo optionInfo: optionList){
            totalDelta += optionInfo.getDelta();
            totalGamma += optionInfo.getGamma();
        }

        totalDelta += basicFutureInfo.getDelta()*number;
        double upperGamma = totalGamma;

        double St = getFuturePrice(target);

        String startDate = futureInfoClient.getPrimaryDate(target);
        String endDate = DateUtil.target2Date(target);

        varList.put("number", number);
        varList.put("totalDelta", totalDelta);
        varList.put("lowerGamma", lowerGamma);
        varList.put("upperGamma", upperGamma);
        varList.put("St", St);
        varList.put("startDate", startDate);
        varList.put("endDate", endDate);

        String result = restTemplate.getForObject(url, String.class, varList);
        System.out.println(result);

    }


    /**
     *
     * @param basicOptionInfo
     * @return 针对不同种类期权计算期权基本信息而提取出的公共块
     */
    private Map<String, Object> getVarList(BasicOptionInfo basicOptionInfo){
        Map<String, Object> varList = new TreeMap<String, Object>();

        varList.put("St", getFuturePrice(basicOptionInfo.getTarget()));
        varList.put("startDate", DateUtil.normalizeDate(basicOptionInfo.getTradeDate()));
        varList.put("endDate", DateUtil.target2Date(basicOptionInfo.getTarget()));
        varList.put("K", basicOptionInfo.getExecutivePrice());
        varList.put("sigmma", getFutureSigmma(basicOptionInfo.getTarget()));

        if(basicOptionInfo instanceof BaOptionInfo){
            BaOptionInfo baOptionInfo = (BaOptionInfo) basicOptionInfo;
            varList.put("H", baOptionInfo.getH());
        }


        return varList;
    }



    /**
     *
     * @param target 期权标的
     * @return 该期权的前收盘价
     */
    private double getFuturePrice(String target){
        double price = futureInfoClient.getTemporaryInfo(target);
        System.out.println("The St is: "+price);
        return price;
    }


    private double getFutureSigmma(String target){
        BasicFutureInfo basicFutureInfo = futureDao.findOne(target);
        return basicFutureInfo.getSigma();
    }

    /**
     *
     * @param target 期权标的
     * @return 该期权的历史收盘数据
     */
    private List<Double> getFutureHistoryPrice(String target){
        return futureInfoClient.getHistoryInfo(target);
    }
}
