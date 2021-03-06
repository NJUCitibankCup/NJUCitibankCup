package nju.citicup.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2016/9/7.
 */
public class DateUtil {

    public static Date getCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String dateStr = formatter.format(today);
        return DateUtil.str2Date(dateStr);
    }

    public static String target2Date(String target){
        int length = target.length();
        String dateStr = target.substring(length-4, length);
        String yearStr = "20"+dateStr.substring(0, 2);
        String monthStr = dateStr.substring(2,4);
        String dayStr = "10";

        return yearStr+"-"+monthStr+"-"+dayStr;
    }

    public static String normalizeDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static Date str2Date(String dateStr){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
