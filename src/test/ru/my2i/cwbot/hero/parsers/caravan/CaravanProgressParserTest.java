package ru.my2i.cwbot.hero.parsers.caravan;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my2i.cwbot.hero.OtherHero;
import ru.my2i.cwbot.model.Castle;
import ru.my2i.cwbot.model.caravan.CaravanLooting;
import ru.my2i.cwbot.model.caravan.CaravanLootingStatus;

import static org.testng.Assert.*;

public class CaravanProgressParserTest {

  private CaravanProgressParser parser;

  @BeforeMethod
  public void setUp() throws Exception {
    parser = new CaravanProgressParser();
  }

  @Test
  public void testTryParseWaiting() throws Exception {
    String message = getExampleBeginMessage();
    CaravanLooting expected = getExampleBeginLooting();
    CaravanLooting actual = parser.tryParse(message);
    assertEquals(actual, expected);
  }

  @Test
  public void testTryParseDetecting() throws Exception {
    String message = getExampleDetectMessage();
    CaravanLooting expected = getExampleDetectLooting();
    CaravanLooting actual = parser.tryParse(message);
    assertEquals(actual, expected);
  }

  @Test
  public void testTryParseComplete() throws Exception {
    String message = getExampleCompleteMessage();
    CaravanLooting expected = getExampleCompleteLooting();
    CaravanLooting actual = parser.tryParse(message);
    assertEquals(actual, expected);
  }

  @Test
  public void testTryParseIncomplete() throws Exception {
    String message = getExampleIncompleteMessage();
    CaravanLooting expected = getExampleIncompleteLooting();
    CaravanLooting actual = parser.tryParse(message);
    assertEquals(actual, expected);
  }



  private CaravanLooting getExampleBeginLooting() {
    CaravanLooting looting = new CaravanLooting();
    looting.setStatus(CaravanLootingStatus.WAITING);
    return looting;
  }

  private String getExampleBeginMessage() {
    return "Ты отправился грабить КОРОВАНЫ. Доберешься до ближайшего через 2 минут.";
  }

  private CaravanLooting getExampleDetectLooting() {
    OtherHero hero = new OtherHero();
    hero.setCastle(Castle.WHITE);
    hero.setName("Bchela");
    CaravanLooting looting = new CaravanLooting();
    looting.setStatus(CaravanLootingStatus.LOOTING);
    looting.setEnemy(hero);
    return looting;
  }

  private String getExampleDetectMessage() {
    return "Рядом с КОРОВАНОМ вы увидели воина \uD83C\uDDE8\uD83C\uDDFEБелого замка - Bchela. Будем надеяться, что он не заметит вас.";
  }

  private CaravanLooting getExampleCompleteLooting() {
    CaravanLooting looting = new CaravanLooting();
    looting.setStatus(CaravanLootingStatus.COMPLETE);
    looting.setGoldProfit(17);
    looting.setExpProfit(25);
    return looting;
  }

  private String getExampleCompleteMessage() {
    return "Засохший маркер не обратил на вас внимания. КОРОВАН ваш - вы получили 17 золотых монет и 25 опыта.";
  }

  private CaravanLooting getExampleIncompleteLooting() {
    CaravanLooting looting = new CaravanLooting();
    looting.setStatus(CaravanLootingStatus.INCOMPLETE);
    looting.setGoldProfit(-5);
    return looting;
  }

  private String getExampleIncompleteMessage() {
    return "Вас задержал Bchela, пришлось откупиться. Потрачено: -5 золотых монет.";
  }

}