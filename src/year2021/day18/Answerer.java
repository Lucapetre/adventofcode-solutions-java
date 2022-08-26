package year2021.day18;

import java.util.List;

class Answerer {

    SnailfishNumber sum;
    final List<String> input;
    public Answerer(List<String> input) {

        this.input = input;
        this.sum = new SnailfishNumber(input.get(0));

        for (int i = 1; i < input.size(); i++) {
            SnailfishNumber number = new SnailfishNumber(input.get(i));
            sum = sum.addNumber(number);
        }
    }

    public int part1Answer() {
        return sum.getTotalMagnitude();
    }

    public int part2Answer() {
        int maxMagnitude = 0;

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.size(); j++) {
                if(i != j) {
                    SnailfishNumber nr1 = new SnailfishNumber(input.get(i));
                    SnailfishNumber nr2 = new SnailfishNumber(input.get(j));
                    SnailfishNumber sumNumber = nr1.addNumber(nr2);
                    maxMagnitude = Math.max(maxMagnitude, sumNumber.getTotalMagnitude());
                }
            }
        }

        return maxMagnitude;
    }

    @Override
    public String toString() {
        return "Part 1:\n" +
                "Total magnitude: " + part1Answer() + "\n" +
                "Part 2:\n" +
                "Max magnitude: "+ part2Answer();
    }
}
