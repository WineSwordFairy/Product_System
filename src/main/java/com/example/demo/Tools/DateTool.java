package com.example.demo.Tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {

    public static Date StringToDate(String strDate) {

        //注意：SimpleDateFormat构造函数的样式与strDate的样式必须相符
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd"); //加上时间
        //必须捕获异常
        try {
            Date date = simpleDateFormat.parse(strDate);
            return date;
        } catch (ParseException px) {
            px.printStackTrace();
            return null;
        }
    }
}
