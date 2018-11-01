package impl;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import types.SearchTopic;

public class SearchMessageListenerImpl implements MessageListener<SearchTopic> {
    public void onMessage(Message<SearchTopic> message) {
        //TODO: retrieve data from message and search
    }
}
