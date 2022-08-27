package year2021.day22;

import java.util.List;

class Answerer {

    private final SmallCubeGrid smallCubeGrid;
    private final CubeCalculator cubeCalculator;
    public Answerer(List<String> input) {
        this.smallCubeGrid = new SmallCubeGrid(input);
        this.cubeCalculator = new CubeCalculator(input);
    }

    public int part1Answer() {
        return smallCubeGrid.countOnCubes();
    }

    public long part2Answer() {
        return cubeCalculator.totalVolume();
    }

    @Override
    public String toString() {
        return "Part 1:\n" +
                "Number of cubes in 50 range: " + part1Answer() + "\n" +
                "Part 2:\n" +
                "Number of cubes on everywhere: " + part2Answer();
    }
}
