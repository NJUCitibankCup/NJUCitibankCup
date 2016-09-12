package nju.citicup.common.vo;

import java.util.List;

/**
 * Created by nians on 2016/9/13.
 */
public class ListDataWrapper {
    private String msg;
    private String condition;
    private List<Object> data;

    public ListDataWrapper() {

    }

    public ListDataWrapper(String msg, String condition, List<Object> data) {
        this.msg = msg;
        this.condition = condition;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}
