package core;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import enums.TopicType;
import impl.SearchMessageListenerImpl;
import impl.SettingChangeListenerImpl;
import impl.StoreMessageListenerImpl;
import types.SearchTopic;
import types.SettingChangeTopic;
import types.StoreTopic;
import util.AppUtil;


public class SubscriberFactory {
    public static HazelcastInstance hz = AppUtil.hazelcastInstance;

    public void initToSubscribeAllTopic(){
        subscribeToSearchTopic();
        subscribeToStoreTopic();
        subscribeToAppSettingChangeTopic();
    }

    public void subscribeToStoreTopic() {
        ITopic<StoreTopic> topic = hz.getTopic(TopicType.STORE.toString());
        topic.addMessageListener(new StoreMessageListenerImpl());
    }

    public void subscribeToSearchTopic() {
        ITopic<SearchTopic> topic = hz.getTopic(TopicType.SEARCH.toString());
        topic.addMessageListener(new SearchMessageListenerImpl());
    }

    public void subscribeToAppSettingChangeTopic() {
        ITopic<SettingChangeTopic> topic = hz.getTopic(TopicType.SETTING_CHANGE.toString());
        topic.addMessageListener(new SettingChangeListenerImpl());
    }
}
