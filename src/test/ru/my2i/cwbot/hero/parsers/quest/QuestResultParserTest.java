package ru.my2i.cwbot.hero.parsers.quest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my2i.cwbot.model.QuestResult;
import ru.my2i.cwbot.model.kraft.KraftItem;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class QuestResultParserTest {

  private QuestResultParser parser;

  @BeforeMethod
  public void setUp() {
    parser = new QuestResultParser();
  }

  @Test
  public void testTryParse() throws Exception {
    String message = getExampleMessage();
    QuestResult expected = getExampleResult();
    QuestResult actual = parser.tryParse(message);
    assertEquals(actual, expected);
  }


  private String getExampleMessage() {
    return "В лесу ты встретил странного седого человека со шрамом на лице. За компанию, он согласился научить тебя техникам борьбы с монстрами.\n" +
        "\n" +
        "Ты заработал: 29 опыта и 4 золотых монет.\n" +
        "Получено: Нитки (2)\n" +
        "Получено: Порошок (1)\n" +
        "Получено: Плотная ткань (1)";
  }

  private QuestResult getExampleResult() {

    Map<KraftItem, Integer> items = new HashMap<>();
    items.put(KraftItem.THREAD, 2);
    items.put(KraftItem.POWDER, 1);
    items.put(KraftItem.DENSE_TEXTURE, 1);

    QuestResult result = new QuestResult();
    result.setItems(items);
    result.setExp(29);
    result.setGold(4);

    return result;
  }

}
