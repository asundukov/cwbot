package ru.my2i.cwbot.hero.parsers.caravan;

import ru.my2i.cwbot.hero.Hero;
import ru.my2i.cwbot.hero.OtherHero;
import ru.my2i.cwbot.hero.parsers.AbstractParser;
import ru.my2i.cwbot.model.Castle;
import ru.my2i.cwbot.model.caravan.CaravanLooting;
import ru.my2i.cwbot.model.caravan.CaravanLootingStatus;

public class CaravanProgressParser extends AbstractParser {
  static private final String NAME_REGEXP = " замка \\- (.+)\\. ";
  static private final String CASTLE_REGEXP = "вы увидели воина .{2}(.+) замка";
  static private final String INCOMPLETE_GOLD_REGEXP = " Потрачено: \\-(\\d+) золотых";
  static private final String COMPLETE_GOLD_REGEXP = " вы получили (\\d+) золот";
  static private final String COMPLETE_EXP_REGEXP = " и (\\d+) опыт";

  @Override
  public CaravanLooting tryParse(String message) {
    if (message.contains("отправился грабить КОРОВАНЫ")) {
      return createWaiting();
    } else if (message.contains("Рядом с КОРОВАНОМ вы увидели")) {
      return createLooting(message);
    } else if (message.contains("Вас задержал ")) {
      return createIncomplete(message);
    } else if (message.contains("КОРОВАН ваш")) {
      return createComplete(message);
    }
    return null;
  }

  @Override
  public String handle(Hero hero, Object data) {
    return null;
  }

  private CaravanLooting createWaiting() {
    CaravanLooting looting = new CaravanLooting();
    looting.setStatus(CaravanLootingStatus.WAITING);
    return looting;
  }

  private CaravanLooting createLooting(String message) {
    CaravanLooting looting = new CaravanLooting();
    looting.setStatus(CaravanLootingStatus.LOOTING);
    looting.setEnemy(getEnemy(message));
    return looting;
  }

  private CaravanLooting createIncomplete(String message) {
    CaravanLooting looting = new CaravanLooting();
    looting.setStatus(CaravanLootingStatus.INCOMPLETE);
    looting.setGoldProfit(getIncompleteProfit(message));
    return looting;
  }

  private CaravanLooting createComplete(String message) {
    CaravanLooting looting = new CaravanLooting();
    looting.setStatus(CaravanLootingStatus.COMPLETE);
    looting.setGoldProfit(getCompleteProfit(message));
    looting.setExpProfit(getCompleteExp(message));
    return looting;
  }

  private int getCompleteExp(String message) {
    return Integer.valueOf(getFirstStringByRegexp(message, COMPLETE_EXP_REGEXP));
  }

  private int getCompleteProfit(String message) {
    return Integer.valueOf(getFirstStringByRegexp(message, COMPLETE_GOLD_REGEXP));
  }


  private int getIncompleteProfit(String message) {
    return -Integer.valueOf(getFirstStringByRegexp(message, INCOMPLETE_GOLD_REGEXP));
  }

  private OtherHero getEnemy(String message) {
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
