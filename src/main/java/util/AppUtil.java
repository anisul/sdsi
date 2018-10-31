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
    public static int searchConvergenceUpperBound;

    public static HazelcastInstance hazelcastInstance;

    /**
    * Load properties from the app.properties file
    */
    public static void loadProperties(){
        lengthOfData = Integer.parseInt(rb.getString("node.data.length"));
        chunkCount = Integer.parseInt(rb.getString("node.chunk.count"));
        peerCount = Integer.parseInt(rb.getString("member.peer.count"));
        hammingThreshold = Integer.parseInt(rb.getString("core.hamming.threshold"));
        binaryConversionThreshold = Integer.parseInt(rb.getString("core.binary.conversion.threshold"));
        searchConvergenceUpperBound = lengthOfData/2;
    }

    /**
    * Generates random binary address of given length
    * @param lengthOfAddress length of the address
    * @return random binary address of given length
    */
    public static int[] generateBinaryRandomAddress(int lengthOfAddress) {
        int[] data = new int[lengthOfAddress];
        Random r = new Random();

        for (int i = 0; i < data.length; i++) {
            data[i] = r.nextInt(2);
        }
        return data;
    }

    /**
     * calculates the hamming distance
     * @param data1 one of two inputs to calculate hamming distance
     * @param data2 one of two inputs to calculate hamming distance
     * @return hamming distance
     */
    public static int calculateHammingDistance(int[] data1, int[] data2) {
        int output = 0;
        for (int i = 0; i < data1.length; i++) {
            if (data1[i] == data2[i]) {
                output++;
            }
        }
        return output;
    }

    /**
     * bipolar conversion
     * @param input address
     * @return bi-polarized address
     */
    public static int[] bipolarConversion(int[] input) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == 0) {
                input[i] = -1;
            }
        }
        return input;
    }

    /**
     * reverse bipolar conversion
     * @param input address
     * @return reverse-bi-polarized address
     */
    public static int[] reverseBipolarConversion(int[] input) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] >= 0) {
                input[i] = 1;
            } else {
                input[i] = 0;
            }
        }
        return input;
    }

    public static int[] sumMemberResultBitwise(int[][] input, int lengthOfData) {
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

    /**
     * Generates random binary address of given length
     * @param input1 address1
     * @param  input2 address2
     * @return bitwise summation of two addresses
     */
    public static int[] sumTwoEntryBitwise(int[] input1, int[] input2) {
        int[] result = new int[input1.length];

        int sum;
        for (int i = 0; i < input1.length; i++) {
            sum = input1[i] + input2[i];
            if (sum < AppUtil.binaryConversionThreshold) {
                result[i] = sum;
            }
        }
        return result;
    }
}
