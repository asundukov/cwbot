package ru.my2i.cwbot.hero.action;

abstract public class ActionFactory {
  public static ActionType typeOf(String msg) {
    String icon = msg.substring(0, 2);

    switch (icon) {
      case "\uD83D\uDECC": return ActionType.FREE;
      case "\uD83D\uDCEF": return ActionType.ARENA;
      case "⚔А": return ActionType.OFFENCE;
      case "\uD83D\uDEE1": return ActionType.DEFENCE;
      case "\uD83C\uDFB2": return ActionType.KOSTI;
      case "\uD83C\uDF32":
      case "\uD83D\uDC2B":
      case "\uD83D\uDD78": return ActionType.QUEST;
    }

    System.out.println(String.format("Cant detect action for %s . Icon: ", msg, icon));

    return ActionType.FREE;
  }
}
