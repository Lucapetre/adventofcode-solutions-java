package year2021.day15;

import utilities.ArrayUtils;
import utilities.LineProcessor;
import utilities.geometry.Point;

import java.util.ArrayList;
import java.util.List;

public class ChitonGrid {

    private final int size; // grid is a square
    private final int[][] grid;

    private final int[][] bigGrid;

    private final int sizeMultiplier;

    private int[][] riskLevel;

    private int part;

    public ChitonGrid(List<String> input, int sizeMultiplier) {
        this.grid = LineProcessor.getDigitsFromStringList(input);
        this.size = input.size();

        this.sizeMultiplier = sizeMultiplier;
        this.bigGrid = new int[bigSize()][bigSize()];
        for (int i = 0; i < bigSize(); i++) {
            for (int j = 0; j < bigSize(); j++) {
                int add = i / size + j / size;
                int gridX = i % size;
                int gridY = j % size;
                bigGrid[i][j] = (grid[gridX][gridY] + add - 1) % 9 + 1;
                // 1-9 - 1 -> 0-8, (0-8 + x) % 9 -> 0-8 + 1 -> 1-9
            }
        }

        this.part = 0;
    }

    private int bigSize() {
        return size * sizeMultiplier;
    }

    public void calculateMinimumRiskLevel(Point start,boolean bigGrid) {

        int[][] riskGrid = this.bigGrid;
        if(!bigGrid)
        {
            riskGrid = this.grid;
        }
        this.riskLevel = new int[riskGrid.length][riskGrid.length];
        ArrayUtils.fillMatrix(riskLevel,0);

        // lee algorithm
        List<Point> queue = new ArrayList<>();
        queue.add(start);
        while(!queue.isEmpty()) {
            Point current = queue.get(0);
            queue.remove(0);
            List<Point> adjacentPoints = current.getAdjacentPoints();
            for (Point point:adjacentPoints) {
                if (point.inBounds(riskGrid.length,riskGrid.length)) {
                    if(!point.equals(start) && point.at(riskLevel) == 0 ||
                            (point.at(riskLevel) > current.at(riskLevel) + point.at(riskGrid))) {
                        point.set(riskLevel,current.at(riskLevel) + point.at(riskGrid));
                        queue.add(point);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        if(part == 0) {
            part++;
            return "Part 1:\n" +
                    "Risk level to end: " + riskLevel[size - 1][size - 1];
        }
        return "Part 2:\n" +
                "Risk level to end: " + riskLevel[bigSize()-1][bigSize()-1];
    }

}
