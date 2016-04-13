package ru.my2i.cwbot.hero.parsers.status;

import ru.my2i.cwbot.hero.Hero;
import ru.my2i.cwbot.hero.action.ActionFactory;
import ru.my2i.cwbot.hero.parsers.AbstractParser;
import ru.my2i.cwbot.model.Castle;
import ru.my2i.cwbot.model.HeroStatusStats;

public class HeroStatusParser extends AbstractParser {
  static private final String NAME_REGEXP = "\\uD83C\\uDDEA\\uD83C\\uDDFA(.*),";
  static private final String LEVEL_REGEXP = "\\uD83C\\uDFC5Уровень: (\\d+)";
  static private final String CASTLE_COLOUR_REGEXP = ", Воин (.*) замка\\n";
  static private final String EXP_REGEXP = "\\uD83D\\uDD25Опыт: (\\d+) из \\d+\\n";
  static private final String NEXT_LEVEL_REGEXP = "\\uD83D\\uDD25Опыт: \\d+ из (\\d+)\\n";
  static private final String GOLD_REGEXP = "\\uD83D\\uDCB0Золото: (\\d+)\\n";
  static private final String STAMINA_REGEXP = "\\uD83D\\uDD0BВыносливость: (\\d+) из \\d+\\n";
  static private final String MAX_STAMINA_REGEXP = "\\uD83D\\uDD0BВыносливость: \\d+ из (\\d+)\\n";
  static private final String CURRENT_ACTION_REGEXP = " Состояние:\\n(.*)$";

  @Override
  public HeroStatusStats tryParse(String message) {
    if (!message.contains("Битва пяти замков через ")) {
      return null;
    }
    HeroStatusStats stats = new HeroStatusStats();
    stats.setCastle(getCastle(message));
    stats.setName(getName(message));
    stats.setLevel(getLevel(message));
    stats.setNextLevelExp(getNextLevelExp(message));
    stats.setExp(getExp(message));
    stats.setGold(getGold(message));
    stats.setStamina(getStamina(message));
    stats.setMaxStamina(getMaxStamina(message));
    stats.setCurrentAction(ActionFactory.typeOf(getAction(message)));
    return stats;
  }

  @Override
  public String handle(Hero hero, Object data) {
    if (data instanceof HeroStatusStats) {
      HeroStatusStats stats = (HeroStatusStats) data;
      System.out.println(String.format("%s", stats));
      hero.setStats(stats);
    }
    return null;
  }

  private static String getName(String message) {
    return getFirstStringByRegexp(message, NAME_REGEXP);
  }

  private static Integer getLevel(String message) {
    return Integer.valueOf(getFirstStringByRegexp(message, LEVEL_REGEXP));
  }

  private static Castle getCastle(String message) {
    return Castle.findByName(getFirstStringByRegexp(message, CASTLE_COLOUR_REGEXP));
  }

  private static Integer getExp(String message) {
    return Integer.valueOf(getFirstStringByRegexp(message, EXP_REGEXP));
  }

  private static Integer getNextLevelExp(String message) {
    return Integer.valueOf(getFirstStringByRegexp(message, NEXT_LEVEL_REGEXP));
  }

  private static Integer getGold(String message) {
    return Integer.valueOf(getFirstStringByRegexp(message, GOLD_REGEXP));
  }

  private static Integer getStamina(String message) {
    return Integer.valueOf(getFirstStringByRegexp(message, STAMINA_REGEXP));
  }

  private static Integer getMaxStamina(String message) {
    return Integer.valueOf(getFirstStringByRegexp(message, MAX_STAMINA_REGEXP));
  }

  private static String getAction(String message) {
    return getFirstStringByRegexp(message, CURRENT_ACTION_REGEXP);
  }

}
