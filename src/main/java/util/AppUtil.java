package util;

import com.hazelcast.core.HazelcastInstance;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

public class AppUtil {
    private static ResourceBundle rb = ResourceBundle.getBundle("app");
    public static int lengthOfData;
    public static int chunkCount;
    public static int peerCount;
    public static int hammingThreshold;
    public static int binaryConversionThreshold;

    public static HazelcastInstance hazelcastInstance;

    public static void loadProperties(){
        lengthOfData = Integer.parseInt(rb.getString("node.data.length"));
        chunkCount = Integer.parseInt(rb.getString("node.chunk.count"));
        peerCount = Integer.parseInt(rb.getString("member.peer.count"));
        hammingThreshold = Integer.parseInt(rb.getString("core.hamming.threshold"));
        binaryConversionThreshold = Integer.parseInt(rb.getString("core.binary.conversion.threshold"));
    }

    public static int[] generateBinaryRandomAddress(int lengthOfAddress) {
        int[] data = new int[lengthOfAddress];
        Random r = new Random();

        for (int i = 0; i < data.length; i++) {
            data[i] = r.nextInt(2);
        }
        return data;
    }

    public static int calculateHammingDistance(int[] data1, int[] data2) {
        int output = 0;
        for (int i = 0; i < data1.length; i++) {
            if (data1[i] == data2[i]) {
                output++;
            }
        }
        return output;
    }

    public static int[] bipolarConversion(int[] input) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == 0) {
                input[i] = -1;
            }
        }
        return input;
    }

    public static int[] sumBitwise(int[][] input, int lengthOfData) {
        int[] output = new int[lengthOfData];
        for (int i = 0; i < lengthOfData; i++) {
            int sum = 0;
            for (int j = 0; j < input.length; j++) {
                if (sum > binaryConversionThreshold) {
                    break;
                }
                sum += input[j][i];
            }
            output[i] = sum;
        }
        return output;
    }
}
