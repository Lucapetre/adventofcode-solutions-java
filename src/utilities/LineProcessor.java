package utilities;

import java.util.ArrayList;
import java.util.List;

public class LineProcessor {

    public static List<Integer> getNumbersFromInputString(String input) { // returns a list with numbers from a string
        List<Integer> integerList = new ArrayList<>();

        String[] parts = input.split("[ ,]"); // see day 6,7
        for (String part : parts) {
            integerList.add(Integer.valueOf(part));
        }

        return integerList;
    }

    public static int[] getDigitsFromString(String line) {
        int[] digits = new int[line.length()];
        for (int i = 0; i < line.length(); i++) {
            digits[i] = (int) line.charAt(i) - (int) '0';
        }
        return digits;
    }

    public static int[][] getDigitsFromStringList(List<String> line) {
        int[][] digits = new int[line.size()][];
        for (int i = 0; i < line.size(); i++) {
            digits[i] = getDigitsFromString(line.get(i));
        }
        return digits;
    }

}
