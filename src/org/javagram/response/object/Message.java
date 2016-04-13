package org.javagram.response.object;


import org.telegram.api.*;

/**
 * Created by Danya on 09.03.2016.
 */
public class Message
{
    private int id;
    private int fromId;
    private int toId;
    private boolean out;
    private boolean unread;
    private int date;
    private String message;

    public Message(TLAbsMessage absMessage)
    {
        if(absMessage instanceof TLMessage)
        {
            TLMessage message = (TLMessage) absMessage;
            id = message.getId();
            fromId = message.getFromId();
            TLAbsPeer peer = message.getToId();
            if (peer instanceof TLPeerUser) {
                toId = ((TLPeerUser) peer).getUserId();
            }
            out = message.getOut();
            unread = message.getUnread();
            date = message.getDate();
            this.message = message.getMessage();
        }
    }

    public int getId() {
        return id;
    }

    public int getFromId() {
        return fromId;
    }

    public int getToId() {
        return toId;
    }

    public boolean isOut() {
        return out;
    }

    public boolean isUnread() {
        return unread;
    }

    public int getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
}
