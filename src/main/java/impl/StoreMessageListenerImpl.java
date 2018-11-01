package impl;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import core.NodeFactory;
import core.SubscriberFactory;
import types.StoreTopic;
import util.AppUtil;

public class StoreMessageListenerImpl implements MessageListener<StoreTopic> {
    SubscriberFactory subscriberFactory = new SubscriberFactory();
    NodeFactory nodeFactory = new NodeFactory();

    public void onMessage(Message<StoreTopic> message) {
        System.out.println("Received data to store: ");
        AppUtil.printIntArray(message.getMessageObject().getData());
        System.out.println("");
        nodeFactory.store(message.getMessageObject().getData());
        nodeFactory.printAllNodesInConsole();
    }
}
