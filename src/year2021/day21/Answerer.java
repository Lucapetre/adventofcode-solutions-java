package year2021.day21;

import utilities.Pair;

import java.util.List;

class Answerer {

    private final SimpleGame simpleGame;
    private final Pair<Long,Long> p1p2Wins;
    public Answerer(List<String> input) {
        this.simpleGame = new SimpleGame(input);
        simpleGame.play();
        QuantumGame quantumGame = new QuantumGame(input);
        p1p2Wins = quantumGame.p1p2WinsInUniverse();
    }

    public int part1Answer() {
        return simpleGame.getProductDieTimesScore();
    }

    public long part2Answer() {
        return Math.max(p1p2Wins.first(),p1p2Wins.second());
    }

    @Override
    public String toString() {
        return "Part 1:\n" +
                "Product of losing player score times times the die was rolled: " + part1Answer() + "\n" +
                "Part 2:\n" +
                "Most wins: " + part2Answer();
    }
}
