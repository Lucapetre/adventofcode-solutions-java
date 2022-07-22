package Year2021.day5;

import utilities.geometry.Line;

import java.util.List;

public class OceanFloorGrid {

    private final int[][] grid;
    private final int size;
    private int part;

    private int overlappingPoints;

    public OceanFloorGrid(int size) {
        this.grid = new int[size][size];
        this.size = size;
        this.overlappingPoints = 0;

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public OceanFloorGrid() { // default size is 1000
        this(1000);
    }

    private void markHorizontalLine(Line line) {
        int x = line.getStart().getX();
        int start = Math.min(line.getStart().getY(),line.getEnd().getY());
        int end = Math.max(line.getStart().getY(),line.getEnd().getY());

        for (int j = start; j <= end; j++) {
            grid[x][j]++;
        }
    }

    private void markVerticalLine(Line line) {
        int y = line.getStart().getY();
        int start = Math.min(line.getStart().getX(),line.getEnd().getX());
        int end = Math.max(line.getStart().getX(),line.getEnd().getX());

        for (int i = start; i <= end; i++) {
            grid[i][y]++;
        }
    }
    private void markHorizontalOrVerticalLine(Line line) {  //Part1
        if (line.isHorizontal()) {
            markHorizontalLine(line);
        } else if (line.isVertical()) {
            markVerticalLine(line);
        }
    }

    private void markDiagonalLine(Line line) {

        if(line.getStart().getX() > line.getEnd().getX()) {
            line.swapEnds(); //flip so x coordinates are in order
        }

        int steps = line.getEnd().getX() - line.getStart().getX(); // basically diagonal distance

        // 1,1->3,3
        if (line.getStart().getY() - line.getStart().getX() == line.getEnd().getY() - line.getEnd().getX()) {

            for (int i = 0; i <= steps; i++) {
                grid[line.getStart().getX() + i][line.getStart().getY() + i]++;
            }
        } else { //1,4 -> 2,3
            for (int i = 0; i <= steps; i++) {
                grid[line.getStart().getX() + i][line.getStart().getY() - i]++;
            }
        }
    }

    /*
    public void markLine(Line line) { //Part2
        if (line.isHorizontalOrVertical()) {
            markHorizontalOrVerticalLine(line);
        } else if (line.isDiagonal()) {
            markDiagonalLine(line);
        }
    }
    */

    public void countOverlappingPoints(int part) {
        this.part = part;
        this.overlappingPoints = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] >= 2) {
                    this.overlappingPoints++;
                }
            }
        }
    }

    public void markHorizontalOrVerticalLinesFromList(List<Line> lineList) {
        for(Line line: lineList) {
            markHorizontalOrVerticalLine(line);
        }
    }

    public void markDiagonalLinesFromList(List<Line> lineList) {
        for(Line line: lineList) {
            if (line.isDiagonal()) {
                markDiagonalLine(line);
            }
        }
    }

    public String toString() {
        return "Part " + part + ":\n" +
                "Overlapping points: " + overlappingPoints;
    }
}
