package com.barsha.userchat.Constants;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class CommonFunctions {
    
    public static Date GetCurrentDate () {
        SimpleDateFormat    sdf                     = new SimpleDateFormat("YYYY-MM-DD");
        String              currentDateString       = ApplicationConstant.SPACES;

        Date        date        = new Date(System.currentTimeMillis());

        return date;
    }

    public static String GetCurrentTime () {
        SimpleDateFormat    sdf                     = new SimpleDateFormat("HH:mm:ss");
        String              currentTimeString       = ApplicationConstant.SPACES;

        Time        time        = new Time(System.currentTimeMillis());

        currentTimeString = sdf.format(time);

        return currentTimeString;
    }
}
