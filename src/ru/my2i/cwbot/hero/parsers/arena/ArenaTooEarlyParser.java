package ru.my2i.cwbot.hero.parsers.arena;

import ru.my2i.cwbot.hero.Hero;
import ru.my2i.cwbot.hero.parsers.Parser;

public class ArenaTooEarlyParser implements Parser {
  @Override
  public Boolean tryParse(String message) {
    if (message.contains("Сражаться можно не чаще чем один раз в час")) {
      return Boolean.TRUE;
    }
    return null;
  }

  @Override
  public String handle(Hero hero, Object data) {
    hero.setLastArenaTime(System.currentTimeMillis() - 1000*60*55);
    return null;
  }
}
