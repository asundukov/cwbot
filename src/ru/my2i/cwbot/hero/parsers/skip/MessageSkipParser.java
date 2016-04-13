package ru.my2i.cwbot.hero.parsers.skip;

import ru.my2i.cwbot.hero.Hero;
import ru.my2i.cwbot.hero.parsers.Parser;

import java.util.Arrays;
import java.util.List;

public class MessageSkipParser implements Parser {

  private final static List<String> messagesForSkipping = Arrays.asList(
      "Смелый вояка! Выбирай врага",
      "Доступные квесты:",
      "Выбирай, какую характеристику ты хочешь улучшить",
      "Слишком долго! Выбор сделан автоматически.",
      "Ветер завывает по окрестным лугам"
  );

  @Override
  public Object tryParse(String message) {
    for(String pattern : messagesForSkipping) {
      if (message.contains(pattern)) {
        return true;
      }
    }
    return null;
  }

  @Override
  public String handle(Hero hero, Object data) {
    return null;
  }
}
