package year2021.day11;

import utilities.ArrayUtils;
import utilities.geometry.Point;

import java.util.List;

public class OctopusGrid {

    private final int[][] grid;
    private final int width;
    private final int height;
    private int flashes;
    private int stepFlashes;

    private int dayOfSync;
    private int day;
    private int part;

    public OctopusGrid(List<String> input) {
        this.height = input.size();
        this.width = input.get(0).length();
        this.flashes = 0;
        this.dayOfSync = -1;
        this.day = 0;
        this.part = 1;

        this.grid = new int[height + 2][width + 2]; // grid will have borders
        ArrayUtils.fillMatrix(grid,11); // set borders to 11, so they won't flash, rest will be filled with input

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i + 1][j + 1] = (int) input.get(i).charAt(j) - (int) '0';
            }
        }
    }

    private void increaseEnergy(Point octopus) {
        int value = octopus.at(grid) + 1;
        octopus.set(grid, value);
        if(octopus.at(grid) == 10) { // flash
            flashes++;
            stepFlashes++;
            List<Point> squareAround = octopus.getSquareAdjacentPoints();
            for(Point point: squareAround) {
                increaseEnergy(point);
            }
        }
    }

    private void step() {

        stepFlashes = 0;
        day++;

        for (int i = 1; i <= height ; i++) {
            for (int j = 1; j <= width ; j++) {
                increaseEnergy(new Point(i,j));
            }
        }

        for (int i = 1; i <= height ; i++) {
            for (int j = 1; j <= width ; j++) {
                if(grid[i][j] >= 10) {
                    grid[i][j] = 0;
                }
            }
        }

        if(dayOfSync == -1 && stepFlashes == width * height) {
            dayOfSync = day;
        }

    }

    public void doNSteps(int number) {
        for (int i = 0; i < number; i++) {
            step();
        }
    }

    public void doStepsUntilSync() {
        this.part = 2;
        while (dayOfSync == -1) {
            step();
        }
    }

    @Override
    public String toString() {
        if (part == 1) {
            return "Part 1:\n" +
                    "Flashes: " + flashes;
        } else if (part == 2) {
            return "Part 2:\n" +
                    "Day of sync: " + dayOfSync;
        }
        return "";
    }
}
