package ru.my2i.cwbot.hero.parsers.interceptor;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my2i.cwbot.hero.Hero;
import ru.my2i.cwbot.hero.OtherHero;
import ru.my2i.cwbot.model.Castle;

import static org.testng.Assert.*;

public class InterceptorParserTest {

  private InterceptorParser parser;

  @BeforeMethod
  public void setUp() throws Exception {
    parser = new InterceptorParser();
  }

  @Test
  public void testTryParse() throws Exception {
    String message = getExampleMessage();
    OtherHero expected = getExampleHero();
    OtherHero actual = parser.tryParse(message);

    assertEquals(actual, expected);
  }

  @Test
  public void testHandle() throws Exception {
    OtherHero hero = getExampleHero();
    assertEquals("/go", parser.handle(new Hero(), hero));
  }

  private String getExampleMessage() {
    return "Вы заметили Анфиса из \uD83C\uDDEC\uD83C\uDDF5Черного замка. Он пытается ограбить КОРОВАН. Чтобы помешать, нажмите /go";
  }

  private OtherHero getExampleHero() {
    OtherHero hero = new OtherHero();
    hero.setName("Анфиса");
    hero.setCastle(Castle.BLACK);
    return hero;
  }

}