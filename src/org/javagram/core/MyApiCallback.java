package org.javagram.core;

import org.telegram.api.TLAbsUpdates;
import org.telegram.api.engine.ApiCallback;
import org.telegram.api.engine.TelegramApi;

/**
 * Created by Danya on 01.08.2015.
 */
public class MyApiCallback implements ApiCallback {
    @Override
    public void onAuthCancelled(TelegramApi telegramApi) {
        System.out.println("MyApiCallback onAuthCancelled invoked");
    }

    @Override
    public void onUpdatesInvalidated(TelegramApi telegramApi) {
        System.out.println("MyApiCallback onUpdatesInvalidated invoked");
    }

    @Override
    public void onUpdate(TLAbsUpdates tlAbsUpdates) {
        System.out.println("MyApiCallback onUpdate invoked");
    }
}
