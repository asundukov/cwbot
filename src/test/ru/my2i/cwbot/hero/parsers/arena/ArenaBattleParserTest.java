package ru.my2i.cwbot.hero.parsers.arena;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my2i.cwbot.hero.Hero;
import ru.my2i.cwbot.model.arena.BattleState;
import ru.my2i.cwbot.model.arena.BattleStatus;
import ru.my2i.cwbot.model.Castle;
import ru.my2i.cwbot.model.arena.Competitor;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ArenaBattleParserTest {

  private ArenaBattleParser parser;

  @BeforeMethod
  public void setUp() {
    parser = new ArenaBattleParser();
  }

  @Test
  public void testFullTryParse() {
    String message = getExampleFirstMessage();
    BattleState expected = getExampleFullState(BattleStatus.WAIT_FOR_ANY_ACTION);
    BattleState actual = parser.tryParse(message);
    assertEquals(actual, expected);
  }

  @Test
  public void testWaitAttackTryParse() {
    String message = getExampleWaitAttackMessage();
    BattleState expected = getExampleLessState(BattleStatus.WAIT_FOR_ATTACK);
    BattleState actual = parser.tryParse(message);
    assertEquals(actual, expected);
  }

  @Test
  public void testWaitDefenceTryParse() {
    String message = getExampleWaitDefenceMessage();
    BattleState expected = getExampleLessState(BattleStatus.WAIT_FOR_DEFENCE);
    BattleState actual = parser.tryParse(message);
    assertEquals(actual, expected);
  }

  @Test
  public void testWaitEnemyTryParse() {
    String message = getExampleWaitEnemyMessage();
    BattleState expected = getExampleLessState(BattleStatus.WAIT_FOR_ENEMY);
    BattleState actual = parser.tryParse(message);
    assertEquals(actual, expected);
  }

  @Test
  public void testHandle() {
    BattleState state = getExampleLessState(BattleStatus.WAIT_FOR_DEFENCE);
    String action = parser.handle(new Hero(), state);
    assertTrue(action.equals("\uD83D\uDEE1головы")
        || action.equals("\uD83D\uDEE1корпуса")
        || action.equals("\uD83D\uDEE1ног"));
  }

  private BattleState getExampleFullState(BattleStatus status) {
    BattleState state = new BattleState(status);
    state.setCompetitors(getExampleCompetitors());
    return state;
  }

  private BattleState getExampleLessState(BattleStatus status) {
    return new BattleState(status);
  }

  private List<Competitor> getExampleCompetitors() {
    return Arrays.asList(getExampleEnemy(), getExampleSelf());
  }

  private Competitor getExampleEnemy() {
    Competitor hero = new Competitor();
    hero.setName("Angry Lumberjack");
    hero.setCastle(Castle.BLACK);
    hero.setHp(105);
    hero.setMaxHp(105);
    return hero;
  }

  private Competitor getExampleSelf() {
    Competitor hero = new Competitor();
    hero.setName("prost");
    hero.setCastle(Castle.BLUE);
    hero.setHp(110);
    hero.setMaxHp(110);
    return hero;
  }

  private String getExampleFirstMessage() {
    return "" +
        "Соперник найден. С вами будет драться воин \uD83C\uDDEC\uD83C\uDDF5Черного замка - Angry Lumberjack.\n" +
        "\n" +
        "\uD83C\uDDEC\uD83C\uDDF5Angry Lumberjack 105/105❤️\n" +
        "VS\n" +
        "\uD83C\uDDEA\uD83C\uDDFAprost 110/110❤️\n" +
        "\n" +
        "выбери точку атаки и точку защиты. У тебя 30 секунд.";
  }

  private String getExampleWaitAttackMessage() {
    return "Хорошо! Осталось определиться с атакой!";
  }

  private String getExampleWaitDefenceMessage() {
    return "Хорошо! Осталось определиться с защитой!";
  }

  private String getExampleWaitEnemyMessage() {
    return "Хороший план, посмотрим что из этого выйдет.";
  }

}