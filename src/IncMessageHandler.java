import org.javagram.handlers.IncomingMessageHandler;

/**
 * Created by Danya on 09.03.2016.
 */
public class IncMessageHandler implements IncomingMessageHandler
{
    @Override
    public Object handle(int userId, String messageText)
    {
        (new Thread(){
            @Override
            public void run()
            {
                System.err.println("Incoming message from " + userId + ": " + messageText);
            }
        }).start();
        return null;
    }
}
