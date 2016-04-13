package org.javagram.core;

import org.telegram.api.engine.TelegramApi;

/**
 * Created by Danya on 28.02.2016.
 */
public class StaticContainer
{
    private static TelegramApi telegramApi;

    public static TelegramApi getTelegramApi() {
        return telegramApi;
    }

    public static void setTelegramApi(TelegramApi telegramApi) {
        StaticContainer.telegramApi = telegramApi;
    }
}
