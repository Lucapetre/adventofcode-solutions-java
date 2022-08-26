package year2021.day20;

import java.util.List;

class Answerer {

    TrenchImage trenchImage;
    public Answerer(List<String> input) {
        this.trenchImage = new TrenchImage(input);
    }

    public int answerPart1() {
        trenchImage.enhanceNTimes(2);
        return trenchImage.numberOfLightPoints();
    }

    public int answerPart2() {
        trenchImage.enhanceNTimes(48); // 2 + 48 = 50
        return trenchImage.numberOfLightPoints();
    }

    @Override
    public String toString() {
        return "Part 1:\n" +
                "Number of lights after 2 days: " + answerPart1() + "\n" +
                "Part 2:\n" +
                "Number of lights after 50 days: " + answerPart2() + "\n";
    }
}
