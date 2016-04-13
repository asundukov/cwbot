package ru.my2i.cwbot.hero.parsers.arena;

import ru.my2i.cwbot.hero.Hero;
import ru.my2i.cwbot.hero.action.ActionType;
import ru.my2i.cwbot.hero.parsers.AbstractParser;
import ru.my2i.cwbot.model.Castle;
import ru.my2i.cwbot.model.HeroStatusStats;
import ru.my2i.cwbot.model.arena.BattleState;
import ru.my2i.cwbot.model.arena.BattleStatus;
import ru.my2i.cwbot.model.arena.Competitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArenaBattleParser extends AbstractParser {

  private static final String COMPETITOR_REGEXP = "(.{2})(.+) (\\d+)/(\\d+)❤️\n";
  private static final String EXP_REGEXP = "Получено: (\\d+)\uD83D\uDD25опыта";

  private static final List<String> attackActions = Arrays.asList(
      "\uD83D\uDDE1в голову",
      "\uD83D\uDDE1по корпусу",
      "\uD83D\uDDE1по ногам");

  private static final List<String> defenceActions = Arrays.asList(
      "\uD83D\uDEE1головы",
      "\uD83D\uDEE1корпуса",
      "\uD83D\uDEE1ног");



  @Override
  public BattleState tryParse(String message) {
    if (message.contains("выбери точку атаки и точку защиты")) {
      return parseAny(message);
    } else if (message.contains("Хорошо! Осталось определиться с защитой!")) {
      return stateWaitForDefence();
    } else if (message.contains("Отлично! Выбери место удара.")) {
      return stateWaitForAttack();
    } else if (message.contains("Хороший план, посмотрим что из этого выйдет.")) {
      return stateWaitForEnemy();
    } else if (message.contains("Получено") && message.contains("/arenatop")) {
      return stateComplete(message);
    }
    return null;
  }

  @Override
  public String handle(Hero hero, Object data) {
    if (data instanceof BattleState) {
      BattleState result = (BattleState) data;
      System.out.println(String.format("%s", result));
      BattleStatus status = result.getStatus();
      if (status.equals(BattleStatus.WAIT_FOR_ANY_ACTION) || status.equals(BattleStatus.WAIT_FOR_ATTACK)) {
        return getAttackAction();
      } else if (status.equals(BattleStatus.WAIT_FOR_DEFENCE)) {
        return getDefenceAction();
      } else if (status.equals(BattleStatus.COMPLETE)) {
        HeroStatusStats stats = hero.getStats();
        stats.setExp(stats.getExp() + result.getExpProfit());
        stats.setCurrentAction(ActionType.FREE);
        hero.setLastArenaTime(System.currentTimeMillis());
        System.out.println(String.format("%s", stats));
      }
    }
    return null;
  }

  private static BattleState stateComplete(String message) {
    BattleState state = new BattleState(BattleStatus.COMPLETE);
    state.setCompetitors(parseCompetitors(message));
    state.setExpProfit(getExp(message));
    return state;
  }

  private static BattleState stateWaitForAttack() {
    return new BattleState(BattleStatus.WAIT_FOR_ATTACK);
  }

  private static BattleState stateWaitForDefence() {
    return new BattleState(BattleStatus.WAIT_FOR_DEFENCE);
  }

  private static BattleState stateWaitForEnemy() {
    return new BattleState(BattleStatus.WAIT_FOR_ENEMY);
  }

  private static BattleState parseAny(String message) {
    BattleState state = new BattleState(BattleStatus.WAIT_FOR_ANY_ACTION);
    state.setCompetitors(parseCompetitors(message));
    return state;
  }

  private static List<Competitor> parseCompetitors(String message) {
    List<Competitor> competitors = new ArrayList<>();
    Pattern p = Pattern.compile(COMPETITOR_REGEXP);
    Matcher m = p.matcher(message);
    while (m.find()) {
      Competitor competitor = new Competitor();
      competitor.setCastle(Castle.findByFlag(m.group(1)));
      competitor.setName(m.group(2));
      competitor.setHp(Integer.valueOf(m.group(3)));
      competitor.setMaxHp(Integer.valueOf(m.group(4)));
      competitors.add(competitor);
    };
    return competitors;
  }

  private static int getExp(String message) {
    return Integer.valueOf(getFirstStringByRegexp(message, EXP_REGEXP));
  }

  private static String getAttackAction() {
    return attackActions.get(getRandomActionNum());
  }

  private static String getDefenceAction() {
    return defenceActions.get(getRandomActionNum());
  }

  private static int getRandomActionNum() {
    return (int) Math.round(Math.floor(Math.random()*3));
  }

}
