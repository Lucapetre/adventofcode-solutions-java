package year2021.day07;

import java.util.List;
import java.util.stream.Collectors;

public class CrabAligner {

    private final List<Integer> crabList;
    private int minDistance;
    private int part;

    public CrabAligner(List<Integer> crabList) {
        this.crabList = crabList.stream().sorted().collect(Collectors.toList());
        this.minDistance = 0;
        this.part = 0;
    }

    public void calculateMinDistancePart1() {

        // start from position x with total of distances y
        // if you walk forward, y increases by the number of crabs before x (including x)
        // and decreases by the number of crabs after x+1 (including x+1)
        // if x is before the middle of the list (middle represents the two numbers in the mid if size is even
        // else just the middle number if size is odd) and it walks forward, the total distance
        // increases by n and decreases by size-n
        // however, because the position is before the middle, n is smaller than half the size so the distance decreases
        // same argument for walking backwards after passing the middle
        // so the minimum distance is calculated by taking x to be the middle number if size is odd or
        // between the middle numbers if size is odd
        // easiest is to take size/2
        // e.g. 1000 numbers 0-999 -> middle is 499-500 and 1000/2 = 500
        // e.g. 1001 numbers 0-1000 -> middle is 500 = 1000/2 exactly

        minDistance = 0;
        int finalPosition = crabList.get(crabList.size() / 2);

        for(Integer crabPosition : crabList) {
            minDistance += Math.abs(crabPosition - finalPosition);
        }
    }

    private static int specialDistance(int position1,int position2) {
        int absDist = Math.abs(position1 - position2);
        return absDist * (absDist + 1) / 2;
    }

    public void calculateMinDistancePart2() {

        minDistance = (int) 2e9;
        int start = crabList.get(0);
        int end = crabList.get(crabList.size() - 1);
        for (int position = start; position <= end; position++) {
            int finalPosition = position;
            int distance = crabList.stream().mapToInt(crab -> specialDistance(crab, finalPosition)).sum();
            minDistance = Math.min(minDistance, distance);
        }

    }

    public String toString() {
        return "Part " + (++part) + ":\n" +
                "Minimum horizontal distance: " + minDistance;
    }

}
