package com.yyj.utils;
/**
 * @author Yanjiang
 * @create 2019-08-01 10:59
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *@ClassName DateUtil
 *@Description TODO
 *@Author Yanjiang
 *@Date 2019/8/1 10:59
 *@Version 1.0
 **/
public class DateUtil {
    public static Date getDate(String date,String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(date);
    }
}
