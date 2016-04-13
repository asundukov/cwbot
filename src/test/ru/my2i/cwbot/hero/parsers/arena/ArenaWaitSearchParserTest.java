package ru.my2i.cwbot.hero.parsers.arena;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArenaWaitSearchParserTest {

  private ArenaWaitSearchParser parser;

  @BeforeMethod
  public void setUp() throws Exception {
    parser = new ArenaWaitSearchParser();
  }

  @Test
  public void testTryParse() throws Exception {
    assertEquals(Boolean.TRUE, parser.tryParse(getExampleMessage()));
  }

  private String getExampleMessage() {
    return "Ищем соперника. Пока соперник не найден, ты можешь в любой момент отменить поиск поединка.";
  }

}