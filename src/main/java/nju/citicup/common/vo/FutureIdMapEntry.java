package nju.citicup.common.vo;

/**
 * Created by admin on 2016/9/13.
 */
public class FutureIdMapEntry {
    String futures_id;
    String futures_name;

    public FutureIdMapEntry(String futures_id, String futures_name) {
        this.futures_id = futures_id;
        this.futures_name = futures_name;
    }

    public String getFutures_id() {
        return futures_id;
    }

    public void setFutures_id(String futures_id) {
        this.futures_id = futures_id;
    }

    public String getFutures_name() {
        return futures_name;
    }

    public void setFutures_name(String futures_name) {
        this.futures_name = futures_name;
    }
}
