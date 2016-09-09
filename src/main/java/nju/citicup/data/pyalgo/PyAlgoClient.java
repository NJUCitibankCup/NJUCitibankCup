package nju.citicup.data.pyalgo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import nju.citicup.common.entity.BasicOptionInfo;
import nju.citicup.common.enumarate.OptionType;
import nju.citicup.common.util.DateUtil;
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

    @Value("${citialgo.server.address}")
    private String serverAddress;

    /**
     *
     * @param basicOptionInfo 基础期权信息
     */
    public String getEuOptionInfo(BasicOptionInfo basicOptionInfo){
        RestTemplate restTemplate = new RestTemplate();

        String uri = serverAddress+"Eu?St={St}&startDate={startDate}" +
                "&endDate={endDate}&K={K}&sigmma={sigmma}";

        Map<String, Object> varList = getVarList(basicOptionInfo);

        String result = restTemplate.getForObject(uri, String.class, varList);
        System.out.println(result);
        return result;
    }

    /**
     *
     * @param basicOptionInfo 基础期权信息
     */
    public String getBaOptionInfo(BasicOptionInfo basicOptionInfo){
        RestTemplate restTemplate = new RestTemplate();

        String uri = serverAddress+"Ba?St={St}&startDate={startDate}" +
                "&endDate={endDate}&K={K}&sigmma={sigmma}&H={H}";

        Map<String, Object> varList = getVarList(basicOptionInfo);

        String result = restTemplate.getForObject(uri, String.class, varList);
        System.out.println(result);
        return result;
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


        return result;
    }

    public void hedgeCriteria(String target){

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
        varList.put("sigmma", 5);

        if(basicOptionInfo.getOptionType() == OptionType.Ba)
            varList.put("H", basicOptionInfo.getH());

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




    /**
     *
     * @param target 期权标的
     * @return 该期权的历史收盘数据
     */
    private List<Double> getFutureHistoryPrice(String target){
        return futureInfoClient.getHistoryInfo(target);
    }
}
