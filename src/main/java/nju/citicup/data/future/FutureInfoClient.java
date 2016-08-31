package nju.citicup.data.future;

import java.util.Map;
import java.util.TreeMap;

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
	 * @return 对应编号的期货的实时数据
	 */
	public String getTemporaryInfo(String code){
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://hq.sinajs.cn/list={code}";
		String result = restTemplate.getForObject(uri, String.class,code);
		System.out.println(result);
		return result;
	}


	/**
	 * @param code 代表期货的编号 如M0(连续期货), M1601(2016年1月份的期货)
	 * @param kind 代表查看的历史期货数据的时间间隔类型 种类有 5 15 30 60和空字符串  分别代表 5min 15min 30min 60min和日K这些类
	 * @return 对应编号的期货的历史数据(鉴于新浪期货api，返回值有可能为空)
	 */
	public String getHitoryInfo(String code, String kind){
		RestTemplate restTemplate = new RestTemplate();
		String uri = "";
		if(kind == "")
			uri = "http://stock2.finance.sina.com.cn/futures/api/json.php/"
				+ "IndexService.getInnerFuturesDailyKLine{kind}?symbol={code}";
		else{
			uri = "http://stock2.finance.sina.com.cn/futures/api/json.php/"
					+ "IndexService.getInnerFuturesMiniKLine{kind}m?symbol={code}";
		}

		Map<String, String> varList = new TreeMap<String, String>();
		varList.put("kind", kind);
		varList.put("code", code);

		String result = restTemplate.getForObject(uri, String.class,varList);
		System.out.println(result);
		return result;
	}
}
