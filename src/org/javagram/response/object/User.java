package org.javagram.response.object;

import org.javagram.TelegramApiBridge;
import org.javagram.core.StaticContainer;
import org.telegram.api.*;
import org.telegram.api.engine.TelegramApi;
import org.telegram.api.upload.TLFile;
import org.telegram.tl.TLBytes;

import java.io.IOException;

/**
 * Created by Danya on 20.09.2015.
 */
public class User
{
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private TLAbsUserProfilePhoto photo;

    public User(TLAbsUser absUser)
    {
        if(absUser instanceof TLUserContact)
        {
            TLUserContact userContact = (TLUserContact) absUser;
            id = userContact.getId();
            firstName = userContact.getFirstName();
            lastName = userContact.getLastName();
            phone = userContact.getPhone();
            photo = userContact.getPhoto();
        }
        else if(absUser instanceof TLUserSelf)
        {
            TLUserSelf userSelf = (TLUserSelf) absUser;
            id = userSelf.getId();
            firstName = userSelf.getFirstName();
            lastName = userSelf.getLastName();
            phone = userSelf.getPhone();
            photo = userSelf.getPhoto();
        }
    }

    public int getId()
    {
        return id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getPhone()
    {
        return phone;
    }

    public byte[] getPhoto(boolean small) throws IOException
    {
        TelegramApi api = StaticContainer.getTelegramApi();

        if(!(photo instanceof TLUserProfilePhoto)) {
            return null;
        }
        TLUserProfilePhoto profilePhoto = (TLUserProfilePhoto) photo;
        TLAbsFileLocation location = small ? profilePhoto.getPhotoSmall() : profilePhoto.getPhotoBig();
        if(!(location instanceof TLFileLocation)) {
            return null;
        }

        TLFileLocation fileLocation = (TLFileLocation) location;
        int dcId = api.getState().getPrimaryDc(); //fileLocation.getDcId();

        TLInputFileLocation inputLocation = new TLInputFileLocation(
            fileLocation.getVolumeId(),
            fileLocation.getLocalId(),
            fileLocation.getSecret()
        );

        TLFile res;
        try {
            res = api.doGetFile(dcId, inputLocation, 0, 1024 * 1024 * 1024);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        return res.getBytes().cleanData();
    }

    public String toString()
    {
        String contact = getFirstName() + " " + getLastName();
        return contact.trim();
    }
}
