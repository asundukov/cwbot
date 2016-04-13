package ru.my2i.cwbot.hero.parsers.quest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my2i.cwbot.model.QuestType;

import static org.testng.AssertJUnit.assertEquals;

public class QuestBeginningParserTest {

  private QuestBeginningParser parser;

  @BeforeMethod
  public void setUp() {
    parser = new QuestBeginningParser();
  }

  @Test
  public void testForestTryParse() {
    String message = getForestExampleMessage();
    assertEquals(QuestType.FOREST, parser.tryParse(message));
  }

  @Test
  public void testCaveTryParse() {
    String message = getCaveExampleMessage();
    assertEquals(QuestType.CAVE, parser.tryParse(message));
  }

  private String getForestExampleMessage() {
    return "Ты отправился искать приключения в лес. Вернешься через 5 минут.";
  }

  private String getCaveExampleMessage() {
    return "Ты отправился искать приключения в пещеру. Вернешься через 5 минут.";
  }
}