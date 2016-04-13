package ru.my2i.cwbot.hero.parsers.arena;

import ru.my2i.cwbot.hero.Hero;
import ru.my2i.cwbot.hero.action.ActionType;
import ru.my2i.cwbot.hero.parsers.Parser;
import ru.my2i.cwbot.model.HeroStatusStats;

public class ArenaWaitSearchParser implements Parser {

  private static final int ARENA_COST = 5;

  @Override
  public Object tryParse(String message) {
    if (message.contains("Ищем соперника.")) {
      return Boolean.TRUE;
    }
    return null;
  }

  @Override
  public String handle(Hero hero, Object data) {
    HeroStatusStats stats = hero.getStats();
    stats.setCurrentAction(ActionType.ARENA);
    stats.setGold(stats.getGold() - ARENA_COST);
    hero.setLastArenaTime(System.currentTimeMillis());
    System.out.print(String.format("Waiting arena: %s", hero));
    return null;
  }
}
