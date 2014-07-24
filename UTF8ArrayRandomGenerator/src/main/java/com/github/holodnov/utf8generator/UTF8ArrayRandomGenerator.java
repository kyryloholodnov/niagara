package com.github.holodnov.utf8generator;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Kyrylo Holodnov
 */
public class UTF8ArrayRandomGenerator {

    public static void main(String[] args) {
        if (args == null || args.length < 2) {
            System.err.println("Usage: java -jar UTF8ArrayRandomGenerator.jar <number_of_bytes> <number_of_iterations>");
            System.exit(1);
        }
        int numberOfBytes = Integer.parseInt(args[0]);
        int numberOfIterations = Integer.parseInt(args[1]);
        System.out.println("Real probability for n = " +
                numberOfBytes + ": " + getRealUTF8Probability(numberOfBytes));
        System.out.println("Approximate probability for n = " + numberOfBytes
        	+ " (iterations = " + numberOfIterations + "): "
        	+ getApproximateUTF8Probability(numberOfBytes, numberOfIterations));
    }

    private static double getRealUTF8Probability(int numberOfBytes) {
        double[] cache = new double[numberOfBytes + 1];
        Arrays.fill(cache, -1);
        return getRealUTF8Probability(numberOfBytes, cache);
    }

    private static double getRealUTF8Probability(int numberOfBytes, double[] cache) {
        if (cache[numberOfBytes] > -1) {
            return cache[numberOfBytes];
        }
        if (numberOfBytes == 0) {
            return 1;
        }
        double result = 0;
        if (numberOfBytes > 0) {
            result += getRealUTF8Probability(numberOfBytes - 1, cache) / 2;
        }
        if (numberOfBytes > 1) {
            result += getRealUTF8Probability(numberOfBytes - 2, cache) / 32;
        }
        if (numberOfBytes > 2) {
            result += getRealUTF8Probability(numberOfBytes - 3, cache) / 256;
        }
        if (numberOfBytes > 3) {
            result += getRealUTF8Probability(numberOfBytes - 4, cache) / 2048;
        }
        if (numberOfBytes > 4) {
            result += getRealUTF8Probability(numberOfBytes - 5, cache) / 16384;
        }
        if (numberOfBytes > 5) {
            result += getRealUTF8Probability(numberOfBytes - 6, cache) / 131072;
        }
        cache[numberOfBytes] = result;
        return result;
    }

    private static double getApproximateUTF8Probability(int numberOfBytes, int numberOfIterations) {
        int passed = 0;
        Random random = new Random(2014);
        byte[] array = new byte[numberOfBytes];
        for (int i = 0; i < numberOfIterations; i++) {
            random.nextBytes(array);
            if (isUTF8(array)) {
                passed++;
            }
        }
        return (passed + 0.0) / numberOfIterations;
    }

    private static boolean isUTF8(byte[] array) {
        Charset utf8 = Charset.forName("UTF-8");
        try {
            utf8.newDecoder().decode(ByteBuffer.wrap(array));
            return true;
        } catch (CharacterCodingException ignored) {
            return false;
        }
    }
}
