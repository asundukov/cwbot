package ru.my2i.cwbot.hero.parsers.quest;

import ru.my2i.cwbot.hero.Hero;
import ru.my2i.cwbot.hero.action.ActionType;
import ru.my2i.cwbot.hero.parsers.Parser;
import ru.my2i.cwbot.model.HeroStatusStats;
import ru.my2i.cwbot.model.QuestType;

public class QuestBeginningParser implements Parser {

  @Override
  public QuestType tryParse(String message) {
    if (message.contains("Ты отправился искать приключения в лес")) {
      return QuestType.FOREST;
    } else if (message.contains("Ты отправился искать приключения в пещеру")) {
      return QuestType.CAVE;
    }
    return null;
  }

  @Override
  public String handle(Hero hero, Object data) {
    if (data instanceof QuestType) {
      QuestType result = (QuestType) data;
      HeroStatusStats stats = hero.getStats();
      stats.setCurrentAction(ActionType.QUEST);
      stats.setStamina(stats.getStamina() - result.getCost());
      System.out.println(String.format("%s", stats));
    }
    return null;
  }
}
