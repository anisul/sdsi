package impl;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import core.NodeFactory;
import types.HzResult;
import types.SearchTopic;
import util.AppUtil;

public class SearchMessageListenerImpl implements MessageListener<SearchTopic> {
    HzResult hzResult = new HzResult();
    NodeFactory nodeFactory = new NodeFactory();

    public void onMessage(Message<SearchTopic> message) {
        int[] input = AppUtil.copyArray(message.getMessageObject().getData());
        int[] result = new int[AppUtil.lengthOfData];

        System.out.println("Received data to search: ");
        AppUtil.printIntArray(input);

        /*String commaSeparatedResult = AppUtil.intArrayToCommaSeparatedString(nodeFactory.search(input));
        hzResult.addToHzSearchResultList(commaSeparatedResult); ;*/

        System.out.println("");
        System.out.println("Reading from shared collection:");
        System.out.println(hzResult.getHzSearchResultList().get(0));
    }
}
