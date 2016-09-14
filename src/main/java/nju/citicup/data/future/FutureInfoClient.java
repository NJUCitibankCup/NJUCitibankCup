package nju.citicup.data.future;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * @author echo
 *该类用于将新浪期货api接口封装 可以获取相应期货的实时数据和历史数据
 */
@Component
public class FutureInfoClient {




	/**
	 * @param code 代表期货的编号 如M0(连续期货), M1601(2016年1月份的期货)
	 * @return 对应编号的期货的昨日收盘数据
	 */
	@Cacheable(value = "temporary")
	public double getTemporaryInfo(String code){
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://hq.sinajs.cn/list={code}";
		String result = restTemplate.getForObject(uri, String.class,code);
		System.out.println(result);

		/*代表了期货数据中 昨日收盘价为数据列表的第六个数据*/
		int index = 5;
		String[] splitList = result.split(",");
		if(splitList.length < index){
			System.out.println("期货实时数据获取出现问题");
			return 0;
		}

		double yesterdayEndPrice = Double.parseDouble(splitList[index]);

		return yesterdayEndPrice;
	}

	@Cacheable(value = "historyDate")
	public String getPrimaryDate(String code){

		List<ArrayList<String>> tempList = getHistoryData(code);
		if(tempList == null || tempList.size() == 0){
			System.out.println("期货历史数据获取出现问题");
			return "";
		}

		ArrayList<String> sourceData = tempList.get(0);
		String sourceDateStr = sourceData.get(0);

		return sourceDateStr;
	}

	/**
	 * @param code 代表期货的编号 如M0(连续期货), M1601(2016年1月份的期货)
	 * @return 对应编号的期货的历史数据(鉴于新浪期货api，返回值有可能为空)
	 */
	@Cacheable(value = "historyInfo")
	public List<Double> getHistoryInfo(String code){

		List<ArrayList<String>> tempList = getHistoryData(code);

		if(tempList == null || tempList.size() == 0){
			System.out.println("期货历史数据获取出现问题");
			return new ArrayList<Double>() ;

		}

		int index = 4;
		List<Double> historyDataList = new ArrayList<>();

		for(ArrayList<String> item: tempList){
			String tempData = item.get(index);
			historyDataList.add(Double.parseDouble(tempData));
		}

		return historyDataList;
	}

	@CacheEvict(cacheNames = {"temporary","historyDate","historyInfo"},allEntries = true)
	public void clearCache(){}


	private List<ArrayList<String>> getHistoryData(String code){
		RestTemplate restTemplate = new RestTemplate();
		String uri ="http://stock2.finance.sina.com.cn/futures/api/json.php/"
				+ "IndexService.getInnerFuturesDailyKLine?symbol={code}";

		Map<String, String> varList = new TreeMap<String, String>();
		varList.put("code", code);

		String result = restTemplate.getForObject(uri, String.class,varList);
		System.out.println(result);

		ObjectMapper mapper = new ObjectMapper();
		List<ArrayList<String>> tempList = new ArrayList<>();
		try {
			tempList = mapper.readValue(result, new TypeReference<List<ArrayList<String>>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}

		return tempList;
	}
}
