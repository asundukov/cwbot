package ru.my2i.cwbot;

import org.javagram.TelegramApiBridge;
import ru.my2i.cwbot.hero.Hero;
import ru.my2i.cwbot.hero.HeroHandler;

public class Bot {

  private int emptyTicks = 1;

  public void run() {
    BotConnector connector = new BotConnector();
    try(TelegramApiBridge apiBridge = connector.getAuthorizedBridge()) {
      BotBridge botBridge = new BotBridge(apiBridge);
      Hero hero = new Hero();
      HeroHandler heroHandler = new HeroHandler(botBridge, hero);
      apiBridge.setIncomingMessageHandler(heroHandler);
      while(true) {
        Thread.sleep(100);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
