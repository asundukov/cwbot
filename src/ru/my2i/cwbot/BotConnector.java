package ru.my2i.cwbot;

import org.javagram.TelegramApiBridge;
import org.javagram.response.AuthAuthorization;
import org.javagram.response.AuthCheckedPhone;
import org.javagram.response.object.User;
import org.javagram.response.object.UserContact;
import org.telegram.api.auth.TLAuthorization;
import org.telegram.mtproto.tl.MTProtoContext;
import org.telegram.tl.TLContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BotConnector {
  final private String prodAddr = "149.154.167.50:443";

  final private int appId = 10000;
  final private String appHash = "f547d0846b55c85ebf2be5a7ed9e7d2e";
  private final BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
  final private String phoneNumber = "77777777777";

  public TelegramApiBridge getAuthorizedBridge() throws IOException {
    TelegramApiBridge apiBridge = new TelegramApiBridge(prodAddr, appId, appHash);
    int selfId = authorize(apiBridge);
    apiBridge.setSelfId(selfId);
    return apiBridge;
  }

  private int authorize(TelegramApiBridge bridge) throws IOException {

    TLAuthorization tlAuthorization = readAuth(bridge);
    User me;
    if (tlAuthorization == null) {
      //Sending validation code
      try {
        bridge.authSendCode(phoneNumber);
      } catch (Exception e) {
        e.printStackTrace();
        return 0;
      }

      System.err.println("Please, enter SMS code: ");
      String smsCode = bReader.readLine().trim();

      //Checking phone number
      AuthCheckedPhone checkedPhone = bridge.authCheckPhone(phoneNumber);
      if (checkedPhone.isRegistered()) {
        //Authorization
        AuthAuthorization auth = bridge.authSignIn(smsCode);
        me = auth.getUser();
        System.err.println("You've signed in; name: " + me.toString() + " and expires: " + auth.getExpires());
        writeAuth(auth.getTlAuthorization());
      } else {
        //Registration
        System.err.println("Please, type the first name:");
        String firstName = bReader.readLine().trim();
        System.err.println("Please, type the last name:");
        String lastName = bReader.readLine().trim();
        AuthAuthorization auth = bridge.authSignUp(smsCode, firstName, lastName);
        me = auth.getUser();
        System.err.println("You've signed up; name: " + me.toString());
      }

    } else {
      AuthAuthorization auth = bridge.authSignIn(tlAuthorization);
      me = auth.getUser();
      System.err.println("You've passed in!!!! name: " + me.toString() + " and expires: " + auth.getExpires());
      writeAuth(auth.getTlAuthorization());
    }

    return me.getId();
  }

  private TLAuthorization readAuth(TelegramApiBridge bridge) {
    File keyFile = new File(phoneNumber);
    try (FileInputStream fis = new FileInputStream(keyFile)) {
      return (TLAuthorization) bridge.getApi().getApiContext().deserializeMessage(fis);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private void writeAuth(TLAuthorization auth) {
    try (FileOutputStream fos = new FileOutputStream(phoneNumber)) {
      fos.write(auth.serialize());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
