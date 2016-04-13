package org.javagram.response;

import org.telegram.api.messages.TLAbsSentMessage;

/**
 * Created by Danya on 09.03.2016.
 */
public class MessagesSentMessage
{
    private int id;
    private int date;
    private int pts;
    private int seq;

    public MessagesSentMessage(TLAbsSentMessage absSentMessage)
    {
        this.id = absSentMessage.getId();
        this.date = absSentMessage.getDate();
        this.pts = absSentMessage.getPts();
        this.seq = absSentMessage.getSeq();
    }

    public int getId() {
        return id;
    }

    public int getDate() {
        return date;
    }

    public int getPts() {
        return pts;
    }

    public int getSeq() {
        return seq;
    }
}
