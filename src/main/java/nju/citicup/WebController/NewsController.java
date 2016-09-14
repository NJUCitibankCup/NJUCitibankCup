package nju.citicup.WebController;

import nju.citicup.common.vo.ListDataWrapper;
import nju.citicup.common.vo.NewsVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by nians on 2016/9/14.
 */
@Controller
public class NewsController {

    @RequestMapping("/api/news")
    @ResponseBody
    public ListDataWrapper getNews(@RequestParam("key_word") String key_word) {
        ListDataWrapper wrapper = new ListDataWrapper();
        wrapper.setMsg("");
        wrapper.setCondition("success");

        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://news.baidu.com/ns?word={key_word}+期货&tn=news&from=news&cl=2&rn=20&ct=1";
        String result = restTemplate.getForObject(uri, String.class,key_word);

        List<Object> data = new ArrayList<>();
        data.addAll(extractNews(result));
        wrapper.setData(data);

        return wrapper;
    }

    private List<NewsVO> extractNews(String src) {
        List<NewsVO> list = new ArrayList<>();
        String[] tempArray = src.split("<h3 class=\"c-title\">");

        for(int i=1;i<6;i++) {
            NewsVO newsVO = refineNews(tempArray[i]);
            list.add(newsVO);
        }
        return list;
    }

    private NewsVO refineNews(String src) {
        String link = src.split("\"")[1];

        String seperator1 = "target=\"_blank\"\n" +
                "            \n" +
                "    >";
        String seperator2 =  "</a></h3>";


        String title = src.split(seperator1)[1].split(seperator2)[0];
        title = title.replace("<em>", "");
        title = title.replace("</em>", "");

        System.out.println(title);
        System.out.println(link);

        NewsVO vo = new NewsVO(title,link);
        return vo;
    }

    public static void main(String[] args){
        String src = "";
        src = src.replace("<h3 class=\"c-title\">", "\n");
        src = src.replace("<div class=\"c-summary c-row \">", "\n");
        System.out.println(src);
    }
}
