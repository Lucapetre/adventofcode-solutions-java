package year2021.day03;

import utilities.BaseManipulator;

class PowerCalculator {

    final int size;
    private final int[] bits1;
    private final int[] bits0;

    private final int[] gammaBits;
    private final int[] epsilonBits;

    private int gammaRate;
    private int epsilonRate;

    public PowerCalculator(int size) {
        this.size = size;
        this.bits0 = new int[size];
        this.bits1 = new int[size];
        this.gammaBits = new int[size];
        this.epsilonBits = new int[size];
    }

    public void processLine(String line) {

        for(int i = 0; i < size; i++) {
            if (line.charAt(i) == '0') {
                bits0[i]++;
            } else {
                bits1[i]++;
            }
        }
    }

    public void getGammaAndEpsilonBits() {
        for (int i = 0; i < size; i++) {
            if (bits0[i] < bits1[i]) {
                gammaBits[i] = 1;
                epsilonBits[i] = 0;
            } else {
                gammaBits[i] = 0;
                epsilonBits[i] = 1;
            }
        }
    }

    public void calculateGammaAndEpsilon() {
        gammaRate = BaseManipulator.convertToBase10(gammaBits, true, 2);
        epsilonRate = BaseManipulator.convertToBase10(epsilonBits, true, 2);
    }

    public String toString() {
        return "Part1:\n" +
                "Gamma and epsilon: " + gammaRate + " " + epsilonRate + "\n" +
                "Gamma * epsilon: " + (gammaRate * epsilonRate);

    }

}
