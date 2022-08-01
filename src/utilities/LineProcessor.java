package utilities;

import java.util.ArrayList;
import java.util.List;

public class LineProcessor {

    public static List<Integer> getNumbersFromInputString(String line) { // returns a list with numbers from a string
        List<Integer> integerList = new ArrayList<>();

        String[] parts = line.split("[ ,]"); // see day 6
        for (String part : parts) {
            integerList.add(Integer.valueOf(part));
        }

        return integerList;
    }

}
