package listener;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

import java.util.Date;

public class MessageListenerImpl implements MessageListener<Date> {
    public void onMessage(Message<Date> m) {
        System.out.println("Received: " + m.getMessageObject());
    }
}
