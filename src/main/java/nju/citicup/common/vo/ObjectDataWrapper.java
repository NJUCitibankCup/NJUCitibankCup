package nju.citicup.common.vo;

/**
 * Created by nians on 2016/9/13.
 */
public class ObjectDataWrapper {
    private String msg="";
    private String condition="success";
    private Object data="";

    public ObjectDataWrapper() {
    }

    public ObjectDataWrapper(String msg, String condition, Object data) {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
