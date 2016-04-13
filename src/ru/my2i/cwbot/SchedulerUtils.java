package ru.my2i.cwbot;

import java.util.Calendar;
import java.util.Date;

public class SchedulerUtils {

  private static Calendar getCalendarOfCurrentTime() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    return calendar;
  }

  public static boolean isCastleTime() {
    Calendar calendar = getCalendarOfCurrentTime();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int min = calendar.get(Calendar.MINUTE);
    return hour % 4 == 0 && (min > 50 || min < 10);
  }

  public static boolean isBarTime() {
    Calendar calendar = getCalendarOfCurrentTime();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    return hour >= 19  || hour < 7;
  }
}
