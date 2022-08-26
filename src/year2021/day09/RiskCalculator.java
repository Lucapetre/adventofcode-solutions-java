package year2021.day09;

import utilities.ArrayUtils;
import utilities.geometry.Point;

import java.util.*;
import java.util.stream.Collectors;

class RiskCalculator {

    private final int lines;
    private final int columns;
    private final int[][] grid;
    private final int[][] visited; // visited by the lee algorithm
    private List<Integer> basinSizes;
    private int part;
    private int riskLevel;

    public RiskCalculator(List<String> input) {

        this.part = 0;
        this.riskLevel = 0;


        this.lines = input.size();
        this.columns = input.get(0).length();
        this.grid = new int[lines + 2][columns + 2]; // the matrix will have a border for easy calculations
        for (int i = 0; i <= lines + 1; i++) {
            for (int j = 0; j <= columns + 1; j++) {
                if(i == 0 || j == 0 || i == lines + 1 || j == columns + 1) {
                    grid[i][j] = 9;
                } else {
                    grid[i][j] = ((int) input.get(i-1).charAt(j-1)) - ((int) '0');
                }
            }
        }

        this.visited = new int[lines + 2][columns + 2];
        ArrayUtils.fillMatrix(visited,0);

        this.basinSizes = new ArrayList<>();
    }

    private List<Integer> getNeighbors(int x,int y) {
        List<Integer> neighbors = new ArrayList<>();
        neighbors.add(grid[x-1][y]);
        neighbors.add(grid[x][y-1]);
        neighbors.add(grid[x+1][y]);
        neighbors.add(grid[x][y+1]);
        return neighbors;
    }

    private boolean isRiskPoint(int x,int y) {
        List<Integer> neighbors = getNeighbors(x,y);
        for (Integer neighbor: neighbors) {
            if(neighbor <= grid[x][y]) {
                return false;
            }
        }
        return true;
    }

    private int basinSize(int x,int y) { // get the size of a basin from a low point
        int totalPoints = 0;
        List<Point> queue = new ArrayList<>();
        queue.add(new Point(x,y));
        visited[x][y] = 1;
        while(queue.size() != 0) {
            Point current = queue.get(queue.size() - 1);
            queue.remove(queue.size() - 1);
            totalPoints++;
            List<Point> adjacentPoints = current.getAdjacentPoints();
            for(Point point:adjacentPoints) {
                if(point.at(visited) == 0 && point.at(grid) != 9) {
                    queue.add(0,point);
                    point.set(visited,1);
                }
            }
        }
        return totalPoints;
    }

    public void determineRiskLevelAndBasins() {
        this.riskLevel = 0;
        for (int i = 1; i <= lines ; i++) {
            for (int j = 1; j <= columns; j++) {
                if(isRiskPoint(i,j)) {
                    riskLevel += (grid[i][j] + 1);
                    basinSizes.add(basinSize(i,j));
                }
            }
        }
        basinSizes = basinSizes.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Part " + (++part) + ":\n" +
                "Risk level: " + riskLevel + "\n" +
                "Part " + (++part) + ":\n" +
                "Basin size product: " + (
                        basinSizes.get(basinSizes.size() - 1) *
                        basinSizes.get(basinSizes.size() - 2) *
                        basinSizes.get(basinSizes.size() - 3)
                );
    }
}
