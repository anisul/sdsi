package impl;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import core.NodeFactory;
import types.SettingChangeTopic;
import util.AppUtil;

public class SettingChangeListenerImpl implements MessageListener<SettingChangeTopic> {
    NodeFactory nodeFactory = new NodeFactory();

    @Override
    public void onMessage(Message<SettingChangeTopic> message) {
        AppUtil.loadPropertiesFromHzMap();
        nodeFactory.initialize();
    }
}
