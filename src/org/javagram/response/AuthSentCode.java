package org.javagram.response;

/**
 * Created by Danya on 04.09.2015.
 */
public class AuthSentCode
{
    private boolean isRegistered;
    private String phoneCodeHash;

    public AuthSentCode(boolean isRegistered, String phoneCodeHash)
    {
        this.isRegistered = isRegistered;
        this.phoneCodeHash = phoneCodeHash;
    }

    public boolean isRegistered()
    {
        return isRegistered;
    }

    public String getPhoneCodeHash()
    {
        return phoneCodeHash;
    }
}
