import com.hazelcast.core.Hazelcast;
import util.AppUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Member {

    public static void main(String[] args) {
        AppUtil.loadProperties();

        int[] data = AppUtil.generateBinaryRandomAddress(10);
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
        }
        //System.out.println(AppUtil.generateBinaryRandomAddress(10));

        //Hazelcast.newHazelcastInstance();
    }
}
