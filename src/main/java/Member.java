import com.hazelcast.core.Hazelcast;
import util.AppUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Member {

    public static void main(String[] args) {
        AppUtil.loadProperties();
        Hazelcast.newHazelcastInstance();
    }
}
