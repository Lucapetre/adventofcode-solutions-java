package utilities;

import java.util.stream.IntStream;

public class BaseManipulator {

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

    public static String convertDecimalToBase(long number, int base, int minDigits) { // up to base 16
        StringBuilder result = new StringBuilder();
        while(number != 0) {
            long digit = number % base;
            number /= base;

            if(digit < 10) {
                result.insert(0, digit);
            } else {
                result.insert(0, (char) (digit - 10 + (int) 'A'));
            }
        }
        while (result.length() < minDigits) {
            result.insert(0,'0');
        }
        return result.toString();
    }

    public static long convertBaseToDecimal(String number, int base) {
        long result = 0;
        for (int i = 0; i < number.length(); i++) {
            char chr = number.charAt(i);
            int digit;
            if('0' <= chr && chr <= '9') {
                digit = chr - '0'; // char digit -> int digit
            } else {
                digit = (int) chr - (int) 'A' + 10; // char digit > 10 -> int number > 10
            }
            result = result * base + (long) digit;
        }
        return result;
    }

    public static String splitHexToBinary(String hexString) {
        StringBuilder binaryString = new StringBuilder();
        for (int i = 0; i < hexString.length(); i++) {
            char chr = hexString.charAt(i);
            binaryString.append(convertDecimalToBase(convertBaseToDecimal(String.valueOf(chr), 16), 2, 4));
        }
        return binaryString.toString();
    }

}
