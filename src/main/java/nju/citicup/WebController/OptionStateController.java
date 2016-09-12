package nju.citicup.WebController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nju.citicup.common.enumarate.SafeType;
import nju.citicup.common.vo.ListDataWrapper;
import nju.citicup.common.vo.OptionStateListVO;
import nju.citicup.common.vo.OptionStateVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nians on 2016/9/12.
 */

@Controller
public class OptionStateController {
    public static void main(String[] args) throws IOException {
        String json = "{\"futures_id\":\"112\",\"futures_name\":\"yumi\",\"number\":1,\"delta\":1,\"safe\":\"safe\"}";

        List<Integer> intList = Arrays.asList(1,2,3,4,5);
        String listJson = "";
        OptionStateVO vo1 = new OptionStateVO("112","yumi",1,1, SafeType.safe);
        OptionStateVO vo2 = new OptionStateVO("113", "dadou", 1, 1, SafeType.safe);

        OptionStateListVO list = new OptionStateListVO("yumi", Arrays.asList(vo1, vo2));

        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(list));
//            listJson = mapper.writeValueAsString(intList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//        OptionStateVO vo3 = mapper.readValue(json,OptionStateVO.class);
//        List<Integer> list = mapper.readValue(listJson, new TypeReference<List<Integer>>() {});




    }

    @RequestMapping("/api/optionState")
    @ResponseBody
    public ListDataWrapper getOptionState() {
        ListDataWrapper wrapper = new ListDataWrapper();
        wrapper.setMsg("");
        wrapper.setCondition("success");

        OptionStateListVO list1 = new OptionStateListVO("玉米", Arrays.asList(new OptionStateVO("011701", "玉米1701", 1, 1, SafeType.safe), new OptionStateVO("011702", "玉米1702", 1, 1, SafeType.warning)));
        OptionStateListVO list2 = new OptionStateListVO("大豆", Arrays.asList(new OptionStateVO("021701", "大豆1701", 1, 1, SafeType.safe), new OptionStateVO("021702", "大豆1702", 1, 1, SafeType.warning)));

        List<Object> data = Arrays.asList(list1, list2);

        wrapper.setData(data);


        return wrapper;
    }

}
