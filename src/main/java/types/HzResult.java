package types;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IList;
import impl.ResultListEntryListener;
import util.AppUtil;

public class HzResult {
    HazelcastInstance hzInstance = AppUtil.hazelcastInstance;
    IList<String> hzSearchResultList;
    ResultListEntryListener resultListEntryListener = new ResultListEntryListener();

    public HzResult(){
        hzSearchResultList = hzInstance.getList("searchResultList");
        hzSearchResultList.addItemListener(resultListEntryListener, false);
    }

    public IList<String> getHzSearchResultList() {
        return hzSearchResultList;
    }

    public void addToHzSearchResultList(String input) {
        hzSearchResultList.add(input);
    }

    public void setHzSearchResultList(IList<String> hzSearchResultList) {
        this.hzSearchResultList = hzSearchResultList;
    }
}
