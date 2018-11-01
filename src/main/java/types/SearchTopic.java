package types;

import enums.TopicType;

import java.io.Serializable;
import java.util.Arrays;

public class SearchTopic implements Serializable {
    private String topicName = TopicType.SEARCH.toString();
    int[] data;

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SearchTopic{" +
                "topicName='" + topicName + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
