package impl;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ItemEvent;
import com.hazelcast.core.ItemListener;
import util.AppUtil;

public class ResultListEntryListener implements ItemListener{
    HazelcastInstance hazelcastInstance = AppUtil.hazelcastInstance;

    public void itemAdded(ItemEvent itemEvent) {

    }

    public void itemRemoved(ItemEvent itemEvent) {

    }
}
