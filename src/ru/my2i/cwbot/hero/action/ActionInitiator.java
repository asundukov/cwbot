package ru.my2i.cwbot.hero.action;

import ru.my2i.cwbot.hero.Hero;
import ru.my2i.cwbot.model.HeroStatusStats;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class ActionInitiator {

  private static final long HOUR_MILLIS = 1000*60*60;

  public static String getAction(Hero hero) {
    HeroStatusStats stats = hero.getStats();
    if (isBeforeCastleTime()) {
      if (stats.getCurrentAction().equals(ActionType.FREE)) {
        return stats.getCastle().getFlag();
      }
    } else if (Objects.equals(stats.getStamina(), stats.getMaxStamina())) {
      return "\uD83C\uDF32Лес";
    } else if (stats.getGold() >= 5 && hero.getLastArenaTime() < (System.currentTimeMillis() - HOUR_MILLIS)) {
      return "\uD83D\uDD0EПоиск соперника";
    } else if (stats.getStamina() >= (stats.getMaxStamina()-1)) {
      return "\uD83C\uDF32Лес";
    }
    return null;
  }

  private static Calendar getCalendarOfCurrentTime() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    return calendar;
  }

  private static boolean isBeforeCastleTime() {
    Calendar calendar = getCalendarOfCurrentTime();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int min = calendar.get(Calendar.MINUTE);
    return ((hour + 1) % 4 == 0) && (min > 53);
  }

  private static boolean isAfterCastleTime() {
    Calendar calendar = getCalendarOfCurrentTime();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int min = calendar.get(Calendar.MINUTE);
    return (hour % 4 == 0) && (min < 5);
  }

  private static boolean isBarTime() {
    Calendar calendar = getCalendarOfCurrentTime();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    return hour >= 19  || hour < 7;
  }

}
