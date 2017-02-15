package org.javagram.response.object;

import org.telegram.api.TLContactStatus;

import java.util.Date;
import static org.javagram.response.Helper.*;

/**
 * Created by Danya on 08.03.2016.
 */
public class ContactStatus
{
    private int userId;
    private Date expires;

    public ContactStatus(TLContactStatus status)
    {
        this.userId = status.getUserId();
        this.expires = intToDate(status.getExpires());
    }

    public int getUserId() {
        return userId;
    }

    public Date getExpires() {
        return expires;
    }
}