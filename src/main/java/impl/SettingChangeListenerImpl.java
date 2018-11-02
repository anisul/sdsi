package impl;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import types.SettingChangeTopic;

public class SettingChangeListenerImpl implements MessageListener<SettingChangeTopic> {
    @Override
    public void onMessage(Message<SettingChangeTopic> message) {

    }
}
