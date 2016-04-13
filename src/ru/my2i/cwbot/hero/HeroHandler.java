package ru.my2i.cwbot.hero;

import org.javagram.handlers.IncomingMessageHandler;
import ru.my2i.cwbot.BotBridge;
import ru.my2i.cwbot.hero.action.ActionInitiator;
import ru.my2i.cwbot.hero.action.ActionType;
import ru.my2i.cwbot.hero.parsers.Parser;
import ru.my2i.cwbot.hero.parsers.arena.ArenaBattleParser;
import ru.my2i.cwbot.hero.parsers.arena.ArenaTooEarlyParser;
import ru.my2i.cwbot.hero.parsers.arena.ArenaWaitSearchParser;
import ru.my2i.cwbot.hero.parsers.caravan.CaravanProgressParser;
import ru.my2i.cwbot.hero.parsers.interceptor.InterceptorParser;
import ru.my2i.cwbot.hero.parsers.quest.QuestBeginningParser;
import ru.my2i.cwbot.hero.parsers.quest.QuestResultParser;
import ru.my2i.cwbot.hero.parsers.skip.MessageSkipParser;
import ru.my2i.cwbot.hero.parsers.status.HeroStatusParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HeroHandler implements IncomingMessageHandler {

  private BotBridge botBridge;
  private Hero hero;

  private List<Parser> parserList = new ArrayList<>(15);

  public HeroHandler(BotBridge botBridge, Hero hero) throws IOException {
    this.botBridge = botBridge;
    this.hero = hero;

    parserList.add(new HeroStatusParser());
    parserList.add(new MessageSkipParser());

    parserList.add(new QuestBeginningParser());
    parserList.add(new QuestResultParser());

    parserList.add(new InterceptorParser());
    parserList.add(new CaravanProgressParser());

    parserList.add(new ArenaWaitSearchParser());
    parserList.add(new ArenaBattleParser());
    parserList.add(new ArenaTooEarlyParser());

    botBridge.sendMessageToBot("\uD83C\uDFC5Герой");
  }

  @Override
  public Object handle(int userId, String messageText)
  {
    (new Thread(() -> {
      if (userId == botBridge.getBotId()) {
        botHandler(messageText);
      } else if (userId == botBridge.getGovId()) {
        govHandler(messageText);
      }
    })).start();
    return null;
  }

  @Override
  public Object selfHandle(int userId, String messageText)
  {
    (new Thread(() -> {
      if (userId == botBridge.getBotId()) {
        selfHandler(messageText);
      }
    })).start();
    return null;
  }

  private void botHandler(String messageText) {
    boolean isParsed = false;
    for(Parser parser : parserList) {
      Object result = parser.tryParse(messageText);
      if (result != null) {
        String command = parser.handle(hero, result);
        isParsed = true;
        if (command != null) {
          sendCommand(command);
        } else {
          if (hero.getStats().getCurrentAction().equals(ActionType.FREE)) {
            command = ActionInitiator.getAction(hero);
            if (command != null) {
              sendCommand(command);
            }
          }
        }
      }
    }

    if (!isParsed) {
      System.out.println(String.format("Cant parse message: \n %s", messageText));
    }
  }

  private void sendCommand(String command) {
    try {
      Thread.sleep(4000);
      botBridge.sendMessageToBot(command);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void govHandler(String messageText) {
    System.out.println("gov: " + messageText);
  }

  private void selfHandler(String messageText) {
    System.out.println("self: " + messageText);
  }
}
