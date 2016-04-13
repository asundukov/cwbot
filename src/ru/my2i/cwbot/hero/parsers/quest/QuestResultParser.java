package ru.my2i.cwbot.hero.parsers.quest;

import ru.my2i.cwbot.hero.Hero;
import ru.my2i.cwbot.hero.action.ActionType;
import ru.my2i.cwbot.hero.parsers.AbstractParser;
import ru.my2i.cwbot.model.HeroStatusStats;
import ru.my2i.cwbot.model.QuestResult;
import ru.my2i.cwbot.model.kraft.KraftItem;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestResultParser extends AbstractParser {

  static private final String EXP_REGEXP = "Ты заработал: (\\d+) опыт";
  static private final String GOLD_REGEXP = "и (\\d+) золотых";
  static private final String ITEMS_REGEXP = "Получено: (.+) \\((\\d+)\\)";

  @Override
  public QuestResult tryParse(String message) {
    if (message.contains("Ты заработал:") && message.contains("Получено")) {
      return parseResult(message);
    } else if (message.contains("Оступившись, ты вляпался в кучу дерьма.")) {
      return emptyResult();
    }
    return null;
  }

  @Override
  public String handle(Hero hero, Object data) {
    if (data instanceof QuestResult) {
      QuestResult result = (QuestResult) data;
      System.out.println(String.format("Quest result: %s", result));
      HeroStatusStats stats = hero.getStats();
      stats.setGold(stats.getGold() + result.getGold());
      stats.setExp(stats.getExp() + result.getExp());
      stats.setCurrentAction(ActionType.FREE);
      System.out.println(String.format("%s", stats));
    }
    return null;
  }

  private QuestResult emptyResult() {
    QuestResult result = new QuestResult();
    result.setItems(Collections.emptyMap());
    return result;
  }


  private static QuestResult parseResult(String message) {
    QuestResult result = new QuestResult();
    result.setExp(getExp(message));
    result.setGold(getGold(message));
    result.setItems(getItems(message));
    return result;
  }

  private static int getExp(String message) {
    return Integer.valueOf(getFirstStringByRegexp(message, EXP_REGEXP));
  }


  private static int getGold(String message) {
    return Integer.valueOf(getFirstStringByRegexp(message, GOLD_REGEXP));
  }

  private static Map<KraftItem, Integer> getItems(String message) {
    Map<KraftItem, Integer> map = new HashMap<>();
    Pattern p = Pattern.compile(ITEMS_REGEXP);
    Matcher m = p.matcher(message);
    while (m.find()) {
      String itemName = m.group(1);
      KraftItem item = KraftItem.findByName(itemName);
      if (item == null) {
        System.out.println(String.format("Unknown kraft item: %s", itemName));
      }
      map.put(item, Integer.valueOf(m.group(2)));
    };
    return map;
  }
}
