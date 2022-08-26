package year2021.day03;

import utilities.BaseManipulator;

import java.util.ArrayList;
import java.util.Arrays;

class LifeSupportRater {

    private final int size;
    private final int number; //of lines

    private final int[] filteredO2Lines; // 1 - is counted 0 - is not counted

    private final int[] filteredCo2Lines;

    private final int[][] bits;

    private int o2Rate;
    private int co2Rate;

    public LifeSupportRater(int size, int number, ArrayList<String> input) {

        this.size = size;
        this.number = number;

        filteredO2Lines = new int[number];
        Arrays.fill(filteredO2Lines, 1);

        filteredCo2Lines = new int[number];
        Arrays.fill(filteredCo2Lines, 1);

        bits = new int[number][];
        for (int i = 0; i < number; i++) {
            bits[i] = new int[size];
        }

        for (int i = 0; i < number; i++) {
            String line = input.get(i);
            for (int j = 0; j < line.length(); j++) {
                bits[i][j] = (line.charAt(j) == '1' ? 1 : 0);
            }
        }
    }
    
    public void filter(int[] counter, int column, int bit) {
        for (int i = 0; i < number; i++) {
            if(bits[i][column] == bit) {
                counter[i] = 0;
            }
        }
    }

    public void step(int column) {
        
        int co2Bits0 = 0;
        int co2Bits1 = 0;
        int o2Bits0 = 0;
        int o2Bits1 = 0;

        for (int i = 0; i < number; i++) {
            if (filteredCo2Lines[i] == 1) {
                co2Bits0 += (1 - bits[i][column]);
                co2Bits1 +=  bits[i][column];
            }

            if (filteredO2Lines[i] == 1) {
                o2Bits0 += (1 - bits[i][column]);
                o2Bits1 +=  bits[i][column];
            }
        }

        int o2 = 0;
        int co2 = 0;
        for (int i = 0; i < number; i++) {
            o2 += filteredO2Lines[i];
            co2 += filteredCo2Lines[i];
        }

        if(o2 > 1) {
            filter(filteredO2Lines, column, (o2Bits1 >= o2Bits0 ? 0 : 1));
        }
        if (co2 > 1) {
            filter(filteredCo2Lines, column, (co2Bits0 <= co2Bits1 ? 1 : 0));
        }
    }
    
    public void allSteps() {
        for (int i = 0; i < size; i++) {
            step(i);
        }
    }
    
    public void getO2AndCo2() {
        for (int i = 0; i < number; i++) {
            if (filteredO2Lines[i] == 1) {
                o2Rate = BaseManipulator.convertToBase10(bits[i],true,2);
            }
            if (filteredCo2Lines[i] == 1) {
                co2Rate = BaseManipulator.convertToBase10(bits[i],true,2);
            }
        }
    }
    
    public String toString () {
        return "Part2:\n" +
                "O2 and Co2: " + o2Rate + " " + co2Rate + "\n" +
                "O2 * Co2: " + (o2Rate * co2Rate);
    }
}
