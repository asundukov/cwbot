package org.javagram.response;

/**
 * Created by Danya on 04.09.2015.
 */
public class AuthCheckedPhone
{
    private boolean isRegistered;
    private boolean isInvited;

    public AuthCheckedPhone(boolean isRegistered, boolean isInvited)
    {
        this.isRegistered = isRegistered;
        this.isInvited = isInvited;
    }

    public boolean isRegistered()
    {
        return isRegistered;
    }

    public boolean isInvited()
    {
        return isInvited;
    }
}