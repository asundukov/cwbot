package ru.my2i.cwbot.hero.parsers;

import ru.my2i.cwbot.hero.Hero;

public interface Parser {
  public Object tryParse(String message);

  public String handle(Hero hero, Object data);
}
