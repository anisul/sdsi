package impl;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import core.SubscriberFactory;
import types.StoreTopic;
import util.AppUtil;

public class StoreMessageListenerImpl implements MessageListener<StoreTopic> {
    SubscriberFactory subscriberFactory = new SubscriberFactory();

    public void onMessage(Message<StoreTopic> message) {
        //TODO: retrieve data from message and store

        System.out.println("Received message! ");
        AppUtil.printIntArray(message.getMessageObject().getData());
        //subscriberFactory.subscribeToStoreTopic();
    }
}
