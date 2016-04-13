package org.javagram.handlers;

public interface IncomingMessageHandler<T>
{
    T handle(int userId, String messageText);

    T selfHandle(int userId, String messageText);
}