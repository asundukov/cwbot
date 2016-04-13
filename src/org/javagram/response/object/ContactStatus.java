package org.javagram.response.object;

import org.telegram.api.TLContactStatus;

/**
 * Created by Danya on 08.03.2016.
 */
public class ContactStatus
{
    private int userId;
    private int expires;

    public ContactStatus(TLContactStatus status)
    {
        this.userId = status.getUserId();
        this.expires = status.getExpires();
    }

    public int getUserId() {
        return userId;
    }

    public int getExpires() {
        return expires;
    }
}