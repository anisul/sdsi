package core;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import enums.TopicType;
import types.SearchTopic;
import types.StoreTopic;
import util.AppUtil;


public class PublisherFactory {
    public static HazelcastInstance hz = AppUtil.hazelcastInstance;

    public void publishStoreTopic(int[] data) {
        StoreTopic storeTopic = new StoreTopic();
        storeTopic.setData(data);
        ITopic<StoreTopic> topic = hz.getTopic(TopicType.STORE.toString());
        topic.publish(storeTopic);
    }

    public void publishSearchTopic(int[] data) {
        SearchTopic searchTopic = new SearchTopic();
        searchTopic.setData(data);
        ITopic<SearchTopic> topic = hz.getTopic(TopicType.SEARCH.toString());
        topic.publish(searchTopic);
    }
}
