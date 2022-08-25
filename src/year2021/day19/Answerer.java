package year2021.day19;

import java.util.List;

public class Answerer {

    private final ScannerMapper scannerMapper;
    public Answerer(List<String> input) {
        scannerMapper = new ScannerMapper(input);
        scannerMapper.alignEveryone();
    }

    public int part1Answer() {
        return scannerMapper.absolutePoints().size();
    }

    public int part2Answer() {
        return scannerMapper.maxDistance();
    }

    @Override
    public String toString() {
        return "Part 1:\n" +
                "Total beacons: " + part1Answer() + "\n" +
                "Part 2:\n" +
                "Max distance between 2 scanners: " + part2Answer();
    }
}
