package com.nutn.utm.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author swshawnwu@gmail.com(ShawnWu)
 */
public class DateTimeUtils {

    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    public static LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }

    public static Date convertToDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static Date convertToTime(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date t = null;
        try {
            t = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static String convertTimeToString(Date time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(time);
    }

    public static LocalTime convertToLocalTime(String time) {
        return LocalTime.from(DateTimeFormatter.ISO_LOCAL_TIME.parse(time));
    }

    public static LocalTime convertToLocalTime(Date time) {
        Instant instant = Instant.ofEpochMilli(time.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
    }

    public static LocalDateTime combineIntoToLocalDateTime(String date, String time) {
        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTime = LocalTime.parse(time);
        return LocalDateTime.of(localDate, localTime);
    }

}
