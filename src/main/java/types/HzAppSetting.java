package types;

import com.hazelcast.core.HazelcastInstance;
import util.AppUtil;

import java.util.Map;

public class HzAppSetting {
    HazelcastInstance hzInstance = AppUtil.hazelcastInstance;
    Map<String, String> hzAppSettingMap;

    public HzAppSetting() {
        hzAppSettingMap = hzInstance.getMap("appSettingMap");
    }

    public Map<String, String> getHzAppSettingMap() {
        return hzAppSettingMap;
    }

    public void setHzAppSettingMap(Map<String, String> hzAppSettingMap) {
        this.hzAppSettingMap = hzAppSettingMap;
    }
}
