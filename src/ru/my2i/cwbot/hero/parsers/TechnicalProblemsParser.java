package ru.my2i.cwbot.hero.parsers;

import ru.my2i.cwbot.hero.Hero;

public class TechnicalProblemsParser implements Parser {
  @Override
  public Object tryParse(String message) {
    if (message.contains("Технические работы в замке. Попробуй позже.")) {
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
