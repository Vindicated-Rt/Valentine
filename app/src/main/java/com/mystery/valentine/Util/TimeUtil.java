package com.mystery.valentine.Util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Vindicated-Rt
 * 2020-02-11 20:32
 */

public class TimeUtil {
    public static int getOffsetDay() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date d1 = null;
        try {
            d1 = df.parse("2018-8-20 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d2 = new Date(System.currentTimeMillis());
        long diff = Math.abs(d1.getTime() - d2.getTime());
        return (int) (diff / (1000 * 60 * 60 * 24));
    }
}
