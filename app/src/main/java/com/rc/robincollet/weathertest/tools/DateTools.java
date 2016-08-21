package com.rc.robincollet.weathertest.tools;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * Created by robincollet on 20/08/2016.
 */
public class DateTools {
    public static String getDateFormat(String format, DateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
        return formatter.print(dateTime);
    }

    public static DateTime getDateTimeFromInt(String unixTimeStamp)
    {
        return new DateTime(Long.valueOf(unixTimeStamp) * 1000);
    }
}
