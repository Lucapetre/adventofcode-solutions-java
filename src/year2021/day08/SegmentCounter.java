package year2021.day08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SegmentCounter {

    private final List<String> displays;
    private int easyDigits;
    private int part;
    private int sum;

    public SegmentCounter(List<String> displays) {
        this.displays = displays;
        this.easyDigits = 0;
        this.part = 0;
        this.sum = 0;
    }

    private static String[] splitDisplayLine(String displayString) {
        String[] split = displayString.split(" \\| ");
        if (split.length != 2) {
            throw new IllegalArgumentException("There should be only one ' | ' in the string");
        }
        return split;
    }

    private static List<String> getStringsFromPart(String displayString, int part) {
        if(part < 0 || part > 1) {
            throw new IllegalArgumentException("The display string has only 2 parts - 0 and 1");
        }

        String[] split = splitDisplayLine(displayString);
        String[] partStrings = split[part].split(" ");

        List<String> stringList = new ArrayList<>();

        Collections.addAll(stringList, partStrings);

        return stringList;
    }

    public void countEasyDigits() {
        for (String displayString: displays) {
            List<String> strings = getStringsFromPart(displayString,1);
            easyDigits += strings.stream()
                    .filter(string -> (
                            string.length() == 2 || string.length() == 3 || string.length() == 4 || string.length() == 7
                    )).count();
        }
    }

    public void determineSum() {
        for(String line:displays) {
            Display display = new Display(getStringsFromPart(line,0),getStringsFromPart(line,1));
            display.determineMaps();
            sum += display.getNumber();
        }
    }

    @Override
    public String toString() {
        return "Part " + (++part) + ":\n" +
                "Easy digits: " + easyDigits + "\n" +
                "Part " + (++part) + ":\n" +
                "Sum: " + sum;
    }
}
