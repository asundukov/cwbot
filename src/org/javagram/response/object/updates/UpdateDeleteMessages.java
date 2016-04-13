package org.javagram.response.object.updates;

import org.javagram.response.object.MessagesMessage;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by HerrSergio on 27.04.2016.
 */
public class UpdateDeleteMessages implements Update {
    private ArrayList<Integer> messages;
    private int pts;

    public UpdateDeleteMessages(Collection<Integer> messages, int pts) {
        this.pts = pts;
        this.messages = new ArrayList<>(messages);
    }

    public ArrayList<Integer> getMessages() {
        return messages;
    }

    public int getPts() {
        return pts;
    }
}
