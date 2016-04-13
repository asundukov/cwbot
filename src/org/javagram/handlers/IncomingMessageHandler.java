package org.javagram.handlers;

/**
 * Created by Danya on 09.03.2016.
 */
public interface IncomingMessageHandler<T>
{
    T handle(int userId, String messageText);
}