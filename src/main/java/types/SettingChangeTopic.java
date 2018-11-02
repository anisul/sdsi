package types;

import enums.TopicType;

import java.io.Serializable;

public class SettingChangeTopic implements Serializable {
    private String topicName = TopicType.SETTING_CHANGE.toString();

    @Override
    public String toString() {
        return "SettingChangeTopic{" +
                "topicName='" + topicName + '\'' +
                '}';
    }
}
