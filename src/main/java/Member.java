import com.hazelcast.core.Hazelcast;
import util.AppUtil;

public class Member {

    public static void main(String[] args) {
        AppUtil.loadProperties();
        Hazelcast.newHazelcastInstance();
    }
}
