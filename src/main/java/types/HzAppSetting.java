package types;

import com.hazelcast.core.HazelcastInstance;
import util.AppUtil;

import java.util.Map;

public class HzAppSetting {
    HazelcastInstance hzInstance = AppUtil.hazelcastInstance;
    Map<String, String> hzAppSettingMap;

    public HzAppSetting() {
        hzAppSettingMap = hzInstance.getMap("appSettingMap");
        hzAppSettingMap.put("lengthOfData", String.valueOf(AppUtil.lengthOfData));
        hzAppSettingMap.put("chunkCount", String.valueOf(AppUtil.chunkCount));
        hzAppSettingMap.put("peerCount", String.valueOf(AppUtil.peerCount));
        hzAppSettingMap.put("hammingThreshold", String.valueOf(AppUtil.hammingThreshold));
        hzAppSettingMap.put("binaryConversionThreshold", String.valueOf(AppUtil.binaryConversionThreshold));
        hzAppSettingMap.put("searchConvergenceUpperBound", String.valueOf(AppUtil.searchConvergenceUpperBound));
    }

    public Map<String, String> getHzAppSettingMap() {
        return hzAppSettingMap;
    }

    public void setHzAppSettingMap(Map<String, String> hzAppSettingMap) {
        this.hzAppSettingMap = hzAppSettingMap;
    }

    @Override
    public String toString() {
        return "HzAppSetting{" +
                "hzAppSettingMap=" + hzAppSettingMap +
                '}';
    }
}
