package ru.my2i.cwbot;

import org.javagram.TelegramApiBridge;
import org.javagram.response.MessagesSentMessage;
import org.javagram.response.object.MessagesDialog;
import org.javagram.response.object.User;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class BotBridge {

  private TelegramApiBridge bridge;

  private User botUser;
  private User govUser;

  BotBridge(TelegramApiBridge bridge) throws IOException {
    this.bridge = bridge;
    List<MessagesDialog> messagesDialogList = bridge.messagesGetDialogs(0, Integer.MAX_VALUE);

    botUser = messagesDialogList.stream().filter(messagesDialog -> Objects.equals(messagesDialog.getPeerUser().getFirstName(), "Chat Wars")).findFirst().orElse(null).getPeerUser();
    govUser = messagesDialogList.stream().filter(messagesDialog -> Objects.equals(messagesDialog.getPeerUser().getFirstName(), "Blue Oyster Team")).findFirst().orElse(null).getPeerUser();
  }

  public void sendMessageToBot(String message) throws IOException {
    MessagesSentMessage sentMsg = bridge.messagesSendMessage(botUser, message, Math.round(Math.random()*Integer.MAX_VALUE));
  }

  public int getBotId() {
    return botUser.getId();
  }

  public int getGovId() {
    return govUser.getId();
  }

  public int getSelfId() {
    return bridge.getSelfId();
  }

}
