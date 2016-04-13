package ru.my2i.cwbot.hero.parsers.interceptor;

import ru.my2i.cwbot.hero.Hero;
import ru.my2i.cwbot.hero.OtherHero;
import ru.my2i.cwbot.hero.parsers.AbstractParser;
import ru.my2i.cwbot.model.Castle;

public class InterceptorParser extends AbstractParser {

  static private final String NAME_REGEXP = "Вы заметили (.+) из";
  static private final String CASTLE_REGEXP = "из .{2}(.+) замка";

  @Override
  public OtherHero tryParse(String message) {
    if (message.contains("/go") && message.contains("КОРОВАН")) {
      return parseMessage(message);
    }
    return null;
  }

  @Override
  public String handle(Hero hero, Object data) {
    if (data instanceof OtherHero) {
      OtherHero enemy = (OtherHero) data;
      System.out.println(String.format("Intercept: %s", enemy));
      return "/go";
    }
    return null;
  }

  private OtherHero parseMessage(String message) {
    OtherHero hero = new OtherHero();
    hero.setName(getName(message));
    hero.setCastle(getCastle(message));
    return hero;
  }

  private static String getName(String message) {
    return getFirstStringByRegexp(message, NAME_REGEXP);
  }

  private static Castle getCastle(String message) {
    String castleName = getFirstStringByRegexp(message, CASTLE_REGEXP);
    return Castle.findByName(castleName);
  }

}
