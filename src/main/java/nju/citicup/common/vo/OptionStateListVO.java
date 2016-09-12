package nju.citicup.common.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nians on 2016/9/12.
 */
public class OptionStateListVO {
    private String type;
    private List<OptionStateVO> data;

    public OptionStateListVO() {

    }

    public OptionStateListVO(String type, List<OptionStateVO> data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OptionStateVO> getData() {
        return data;
    }

    public void setData(ArrayList<OptionStateVO> data) {
        this.data = data;
    }
}
