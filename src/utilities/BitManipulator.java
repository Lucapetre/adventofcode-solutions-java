package utilities;

import java.util.stream.IntStream;

public class BitManipulator {

    public static int convertToBase10(int[] bits, boolean leftToRight, int base) {

        int[] copyBits;
        int result = 0;

        if(!leftToRight) {
            copyBits = IntStream.range(0,bits.length).map(i -> bits[bits.length - 1 - i]).toArray();
        } else {
            copyBits = bits;
        }

        for (int bit : copyBits) {
            result = result * base + bit;
        }

        return result;
    }

}
