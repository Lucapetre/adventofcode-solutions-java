package utilities;

import java.util.Arrays;

public class ArrayUtils {

    public static void fillMatrix(int[][] array,int value) {
        for (int[] ints : array) {
            Arrays.fill(ints, value);
        }
    }

    public static void fillMatrix(long[][] array, int value) {
        for (long[] longs : array) {
            Arrays.fill(longs, value);
        }
    }
}
