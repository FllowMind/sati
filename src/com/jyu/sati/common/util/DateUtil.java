/**
 * Copyright 2007-2008 CSN, Mask Development Team.
 */
package com.jyu.sati.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;

/**
 * Date wrapped by using Calendar for simulating JScript usage.<br>
 * Some methods can be also found in class TypeUtil and DateWrapper (which is from
 * MaskSimpleWorkflow).
 * 
 * @see org.apache.commons.lang.time.DateUtils
 * @author sin_sin
 * @version $Revision: 1.17 $
 */
public class DateUtil extends DateUtils {

    /** yyyy-MM-dd'T'HH:mm:ss.SZ */
    public static final String DATETIME_PATTERN_ISO_FULL = "yyyy-MM-dd'T'HH:mm:ss.SZ";
    /** yyyy-MM-dd'T'HH:mm:ss */
    public static final String DATETIME_PATTERN_ISO = "yyyy-MM-dd'T'HH:mm:ss";
    /** yyyy-MM-dd HH:mm:ss.S */
    public static final String DATETIME_PATTERN_DATETIME_FULL = "yyyy-MM-dd HH:mm:ss.S";
    /** yyyy-MM-dd HH:mm:ss */
    public static final String DATETIME_PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    /** yyyy-MM-dd HH:mm */
    public static final String DATETIME_PATTERN_DATETIME_NOSEC = "yyyy-MM-dd HH:mm";
    /** yyyyMMddHHmmss */
    public static final String DATETIME_PATTERN_DATETIME_COMPACT = "yyyyMMddHHmmss";
    /** yyyyMMddHHmm */
    public static final String DATETIME_PATTERN_DATETIME_COMPACT_NOSEC = "yyyyMMddHHmm";
    /** yyyy-MM-dd */
    public static final String DATETIME_PATTERN_DATE = "yyyy-MM-dd";
    /** yyyyMMdd */
    public static final String DATETIME_PATTERN_DATE_COMPACT = "yyyyMMdd";
    /** ddMMMyy, en_US Locale required */
    public static final String DATETIME_PATTERN_DATE_US = "ddMMMyy";
    /** HH:mm:ss */
    public static final String DATETIME_PATTERN_TIME = "HH:mm:ss";
    /** HH:mm */
    public static final String DATETIME_PATTERN_TIME_NOSEC = "HH:mm";
    /** HHmm */
    public static final String DATETIME_PATTERN_TIME_COMPACT = "HHmm";
    
    public static final Integer BEGIN_DATE_OF_WEEK = 0;
	
	public static final Integer END_DATE_OF_WEEK = 1;

    public static Map<Integer, String> getCurrentWeek() throws Exception {
    	/*java.util.Calendar c = java.util.Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(DATETIME_PATTERN_DATE);
        String dateFormat = format.format(new Date());
        
        String[] dateArray = dateFormat.split("-");
        int day_of_week = c.get(java.util.Calendar.DAY_OF_WEEK);
        int week_begin_day = Integer.parseInt(dateArray[2]) - day_of_week + 1;
        String w_begin_day = week_begin_day < 10 ? ("0" + week_begin_day)
                : String.valueOf(week_begin_day);

        int week_end_day = Integer.parseInt(dateArray[2])
                + (java.util.Calendar.DAY_OF_WEEK - day_of_week);
        String w_end_day = week_end_day < 10 ? ("0" + week_end_day)
                : String.valueOf(week_end_day);

        String beginDate = dateArray[0] + "-" + dateArray[1] + "-"
                + w_begin_day;
        String endDate = dateArray[0] + "-" + dateArray[1] + "-" + w_end_day;*/
        
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(DATETIME_PATTERN_DATE);
        String dateFormat = format.format(new Date());
    	calendar.setTime(format.parse(dateFormat));
    	// 本周第几�?
    	int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)-1;
    	if (dayOfWeek == 0) {
    		dayOfWeek = 7;
    	}
    	// 本周第一�?
    	calendar.add(Calendar.DATE, -dayOfWeek+1);
    	String beginDate = format.format(calendar.getTime());
    	// 本周�?后一�?
    	calendar.add(Calendar.DATE, 6);
    	String endDate = format.format(calendar.getTime());

        Map<Integer, String> weekMap = new HashMap<Integer, String>();
        weekMap.put(BEGIN_DATE_OF_WEEK, beginDate);
        weekMap.put(END_DATE_OF_WEEK, endDate);
        
        return weekMap;
    }
    
    /**
     * Get the year.
     * 
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Get the month of the year.
     * 
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    /**
     * Get the day of the month.
     * 
     * @param date
     * @return
     */
    public static int getDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Get the day of the week.
     * 
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * Get the hours of the day.
     * 
     * @param date
     * @return
     */
    public static int getHours(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Get the minutes of the hour.
     * 
     * @param date
     * @return
     */
    public static int getMinutes(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * Get the seconds of the minute.
     * 
     * @param date
     * @return
     */
    public static int getSeconds(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * Get the interval between two times.
     * 
     * @param date1 first time
     * @param date2 second time
     * @return Array of period represented by: days, hours, minutes, seconds,
     *         milliseconds.
     */
    public static long[] timePeriod(Date date1, Date date2) {
        long diff = Math.abs(date2.getTime() - date1.getTime());
        long d = diff / MILLIS_PER_DAY;
        diff %= MILLIS_PER_DAY;
        long h = diff / MILLIS_PER_HOUR;
        diff %= MILLIS_PER_HOUR;
        long m = diff / MILLIS_PER_MINUTE;
        diff %= MILLIS_PER_MINUTE;
        long s = diff / MILLIS_PER_SECOND;
        long ms = diff % MILLIS_PER_SECOND;
        long[] result = new long[5];
        result[0] = d;
        result[1] = h;
        result[2] = m;
        result[3] = s;
        result[4] = ms;
        return result;
    }

    /**
     * Get the interval of minutes between two dates.
     * 
     * @param begDate
     * @param endDate
     * @return
     */
    public static long minuteDiff(Date begDate, Date endDate) {
        long diff = endDate.getTime() - begDate.getTime();
        return diff / MILLIS_PER_MINUTE;
    }

    /**
     * Get the left minutes on begin time passing over the end time.
     * 
     * @param begDate
     * @param endDate
     * @return left time will be add 1, that means if begin time equals end time,
     *         1 minute should be left
     */
    public static long minuteLeft(Date begDate, Date endDate) {
        double diff = endDate.getTime() - begDate.getTime();
        long result = (long) (diff >= 0 ? Math.ceil(diff / MILLIS_PER_MINUTE)
                : Math.floor(diff / MILLIS_PER_MINUTE));
        return result;
    }

    /**
     * Get the interval of hours between two dates.
     * 
     * @param begDate
     * @param endDate
     * @return
     */
    public static long hourDiff(Date begDate, Date endDate) {
        long diff = endDate.getTime() - begDate.getTime();
        return diff / MILLIS_PER_HOUR;
    }

    /**
     * Get the interval of days between two dates.
     * 
     * @param begDate
     * @param endDate
     * @return
     */
    public static int dateDiff(Date begDate, Date endDate) {
        long diff = endDate.getTime() - begDate.getTime();
        return (int) (diff / MILLIS_PER_DAY);
    }

    /**
     * Get the interval of day of year by calendar.
     * 
     * @param begDate
     * @param endDate
     * @return
     */
    public static int dayDiff(Date begDate, Date endDate) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(begDate);
        cal2.setTime(endDate);
        int year1 = cal1.get(Calendar.YEAR);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        return (year2 - year1) * 365 + (day2 - day1);
    }

    /**
     * Get the interval of month between two dates.
     * 
     * @param begDate
     * @param endDate
     * @return
     */
    public static int monthDiff(Date begDate, Date endDate) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(begDate);
        cal2.setTime(endDate);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        int month1 = cal1.get(Calendar.MONTH);
        int month2 = cal2.get(Calendar.MONTH);
        int year = 0, month = 0;
        if (month2 >= month1) {
            year = year2 - year1;
            month = month2 - month1;
        } else {
            year = year2 - year1 - 1;
            month = 12 - (month1 - month2);
        }
        return year * 12 + month;
    }

    /**
     * Get earlier date of two.
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static Date earlierDate(Date date1, Date date2) {
        return date2.before(date1) ? date2 : date1;
    }

    /**
     * Get earlier date of two.<br>
     * Null safe.
     * 
     * @param date1
     * @param date2
     * @return earlier date
     */
    public static Date minDate(Date date1, Date date2) {
        if (null == date1 && null == date2) {
            return null;
        } else if (null == date1) {
            return date2;
        } else if (null == date2) {
            return date1;
        }
        return earlierDate(date1, date2);
    }

    /**
     * Get later date of two.
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static Date laterDate(Date date1, Date date2) {
        return date2.after(date1) ? date2 : date1;
    }

    /**
     * Get later date of two.<br>
     * Null safe.
     * 
     * @param date1
     * @param date2
     * @return later date
     */
    public static Date maxDate(Date date1, Date date2) {
        if (null == date1 && null == date2) {
            return null;
        } else if (null == date1) {
            return date2;
        } else if (null == date2) {
            return date1;
        }
        return laterDate(date1, date2);
    }

    /**
     * Add some date to the specified date.
     * 
     * @param date
     * @param year
     * @param month
     * @param week
     * @param day
     * @return
     */
    public static Date dateAdd(Date date, int year, int month, int week, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.WEEK_OF_YEAR, week);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }

    /**
     * Add some day to the specified date.
     * 
     * @param date
     * @param day
     * @return
     */
    public static Date dateAdd(Date date, int day) {
        return dateAdd(date, 0, 0, 0, day);
    }

    /**
     * Add any time to the specified date.
     * 
     * @param date
     * @param hour
     * @param min
     * @param sec
     * @return
     */
    public static Date timeAdd(Date date, int hour, int min, int sec) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        calendar.add(Calendar.MINUTE, min);
        calendar.add(Calendar.SECOND, sec);
        return calendar.getTime();
    }

    /**
     * Checks if date time is BETWEEN a range of two times. Null-safe.<br>
     * Caution: Usually we expect that time range will be matched as left-closed
     * and right-opened interval. But if range is just represented date only, both
     * closed interval may be expected instead.
     * 
     * @see #betweenOpenRight(Date, Date, Date)
     * @see #betweenDate(Date, Date, Date)
     * @param time time to check
     * @param startTime start of time range, negative infinity by default if null
     * @param endTime end of time range, infinity presented if null
     * @return false if date time is null
     */
    public static boolean between(Date time, Date startTime, Date endTime) {
        if (time == null) {
            return false;
        } else if (startTime != null && endTime == null) {
            return time.compareTo(startTime) >= 0;
        } else if (startTime == null && endTime != null) {
            return time.before(endTime);
        }
        return betweenOpenRight(time, startTime, endTime);
    }

    /**
     * Checks if date is BETWEEN a range of two dates. Null-safe.<br>
     * Only dates are expected, and both closed interval range supposed.<br>
     * Note: if any of <code>Date</code> parameters includes time value, use
     * {@link #getDateOnly(Date)} method first.
     * 
     * @see #between(Date, Date, Date)
     * @see #betweenClose(Date, Date, Date)
     * @param date date to check
     * @param startDate start of date range
     * @param endDate end of date range
     * @return false if date is null
     */
    public static boolean betweenDate(Date date, Date startDate, Date endDate) {
        if (date == null) {
            return false;
        } else if (startDate != null && endDate == null) {
            return date.compareTo(startDate) >= 0;
        } else if (startDate == null && endDate != null) {
            return date.compareTo(endDate) <= 0;
        }
        return betweenClose(date, startDate, endDate);
    }

    /**
     * Both close interval, between [date1, date2]
     * 
     * @param date
     * @param date1
     * @param date2
     * @return
     */
    public static boolean betweenClose(Date date, Date date1, Date date2) {
        return date.compareTo(date1) >= 0 && date.compareTo(date2) <= 0;
    }

    /**
     * Interval left closed, right opened, between [date1, date2)
     * 
     * @param date
     * @param date1
     * @param date2
     * @return
     */
    public static boolean betweenOpenRight(Date date, Date date1, Date date2) {
        return date.compareTo(date1) >= 0 && date.before(date2);
    }

    /**
     * Interval left opened, right closed, between (date1, date2]
     * 
     * @param date
     * @param date1
     * @param date2
     * @return
     */
    public static boolean betweenOpenLeft(Date date, Date date1, Date date2) {
        return date.after(date1) && date.compareTo(date2) <= 0;
    }

    /**
     * Both open interval, between (date1, date2)
     * 
     * @param date
     * @param date1
     * @param date2
     * @return
     */
    public static boolean betweenOpen(Date date, Date date1, Date date2) {
        return date.after(date1) && date.before(date2);
    }

    /**
     * Clear time part, only get date.
     * 
     * @param Datetime
     * @return Date
     */
    public static Date getDateOnly(Date datetime) {
        return stringToDate(dateToString(datetime));
    }

    /**
     * Null-safe date equals method. Do not use <code>equals</code> method for
     * ignoring differences with <code>java.sql.Timestamp</code>
     * 
     * @param date1
     * @param date2
     * @return true if two dates are all the same.
     */
    public static boolean equals(Date date1, Date date2) {
        if (null == date1 && null == date2)
            return true;
        if (null == date1 || null == date2)
            return false;
        return date1.getTime() == date2.getTime();
    }

    /**
     * Format yyyy-MM-dd string to date.
     * 
     * @param String
     * @return Date
     */
    public static Date stringToDate(String date) {
        return stringToDate(date, DATETIME_PATTERN_DATE);
    }

    /**
     * Format yyyy-MM-dd HH:mm:ss string to date.
     * 
     * @param String
     * @return Date
     */
    public static Date stringToDatetime(String date) {
        return stringToDate(date, DATETIME_PATTERN_DATETIME);
    }

    /**
     * Get date with specified string format.
     * 
     * @param String
     * @param format
     * @return Date
     */
    public static Date stringToDate(String date, String format) {
        return stringToDate(date, format, Locale.getDefault(),
                TimeZone.getDefault());
    }

    /**
     * Get date with specified string format and locale.
     * 
     * @param String
     * @param format
     * @param Locale
     * @return Date
     */
    public static Date stringToDate(String date, String format, Locale locale) {
        return stringToDate(date, format, locale, TimeZone.getDefault());
    }

    /**
     * Get date with specified string format and locale and time-zone.
     * 
     * @param String
     * @param format
     * @param Locale
     * @param TimeZone
     * @return Date
     */
    public static Date stringToDate(String date, String format, Locale locale,
            TimeZone zone) {
        Date d = null;
        try {
            if (null != date) {
                SimpleDateFormat df = new SimpleDateFormat(format, locale);
                if (null != zone)
                    df.setTimeZone(zone);
                d = df.parse(date);
            }
        } catch (ParseException e) {
        }
        return d;
    }

    /**
     * Format date to yyyy-MM-dd string.
     * 
     * @param Date
     * @return String
     */
    public static String dateToString(Date date) {
        return dateToString(date, DATETIME_PATTERN_DATE);
    }

    /**
     * Format date to yyyy-MM-dd HH:mm:ss string.
     * 
     * @param Date
     * @return String
     */
    public static String datetimeToString(Date date) {
        return dateToString(date, DATETIME_PATTERN_DATETIME);
    }

    /**
     * Format date to specified format string.
     * 
     * @param Date
     * @param format
     * @return String
     */
    public static String dateToString(Date date, String format) {
        return dateToString(date, format, Locale.getDefault(),
                TimeZone.getDefault());
    }

    /**
     * Format date to specified format and locale string.
     * 
     * @param Date
     * @param format
     * @param Locale
     * @return String
     */
    public static String dateToString(Date date, String format, Locale locale) {
        return dateToString(date, format, locale, TimeZone.getDefault());
    }

    /**
     * Format date to specified format and locale string.
     * 
     * @param Date
     * @param format
     * @param Locale
     * @param TimeZone
     * @return String
     */
    public static String dateToString(Date date, String format, Locale locale,
            TimeZone zone) {
        String s = null;
        if (null != date) {
            SimpleDateFormat df = new SimpleDateFormat(format, locale);
            if (null != zone)
                df.setTimeZone(zone);
            s = df.format(date);
        }
        return s;
    }

    /**
     * Convert date time to GMT 0:00 zone.
     * 
     * @param Date
     * @return Date
     */
    public static Date getUtcDate(Date date) {
        return dateToOtherTimeZone(date, TimeZone.getDefault().getID(), "GMT");
    }

    /**
     * Convert date time to GMT 0:00 zone.
     * 
     * @param Date
     * @param zoneId
     * @return Date
     */
    public static Date getUtcDate(Date date, String zoneId) {
        return dateToOtherTimeZone(date, zoneId, "GMT");
    }

    /**
     * Convert date with supposed time-zone to another specified time-zone.
     * 
     * @param Date
     * @param sourceZone
     * @param targetZone
     * @return Date
     */
    public static Date dateToOtherTimeZone(Date date, String sourceZone,
            String targetZone) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(targetZone));
        calendar.setTime(date);
        int ms = calendar.get(Calendar.MILLISECOND);
        calendar.setTimeZone(TimeZone.getTimeZone(sourceZone));
        calendar.set(Calendar.MILLISECOND, ms);
        return calendar.getTime();
    }

    public static Date offsetDate(Date date, int offset) {
        Calendar calendar = convert(date);
        calendar.add(Calendar.DATE, offset);
        return calendar.getTime();
    }

    private static Calendar convert(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 获取日期区间里所有日�?
     * 
     * @param start
     * @param end
     * @return
     * @throws Exception
     */
    public static List<String> getDateList(String start, String end)
            throws Exception {
        List<String> rlist = new ArrayList<String>();
        Date date = null;
        Calendar cal = Calendar.getInstance();
        date = new SimpleDateFormat("yy-MM-dd").parse(start);
        rlist.add(new SimpleDateFormat("yyyy-MM-dd").format(date));
        cal.setTime(date);
        while (true) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (sdf.format(cal.getTime()).equals(end)) {
                break;
            }
            cal.add(Calendar.DAY_OF_MONTH, 1);
            rlist.add(sdf.format(cal.getTime()));
        }
        return rlist;
    }
    
	
}
