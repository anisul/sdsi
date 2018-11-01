package types;

import enums.TopicType;

import java.io.Serializable;
import java.util.Arrays;

public class StoreTopic implements Serializable {
    String topicName = TopicType.STORE.toString();
    int[] data;

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StoreTopic{" +
                "topicName='" + topicName + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
