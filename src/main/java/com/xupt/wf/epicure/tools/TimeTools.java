package com.xupt.wf.epicure.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: WangFeng
 * Date: 2018-04-30
 * Time: 16:08
 */
public class TimeTools {

    private static String pattern = "yyyy-MM-dd HH:mm:ss";
    private static String pattern_2 = "yyyy-MM-dd-HH-mm-ss-SSS";

    private static SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    private static SimpleDateFormat sdf2 = new SimpleDateFormat(pattern_2);

    public static String formatDate(Date currentDate){
        return sdf.format(currentDate);
    }

    public static String formatFileName(Date currentDate){
        return sdf2.format(currentDate);
    }

}
