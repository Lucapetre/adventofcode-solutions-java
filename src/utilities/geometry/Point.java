package utilities.geometry;

import java.util.ArrayList;
import java.util.List;

public record Point(int x, int y) {

    public List<Point> getAdjacentPoints() {
        List<Point> neighbors = new ArrayList<>();
        neighbors.add(new Point(x - 1, y));
        neighbors.add(new Point(x + 1, y));
        neighbors.add(new Point(x, y - 1));
        neighbors.add(new Point(x, y + 1));
        return neighbors;
    }

    public List<Point> getSquareAdjacentPoints() {
        List<Point> neighbors = new ArrayList<>();
        for (int i = x - 1; i <= x + 1 ; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if(i != x || j != y) {
                    neighbors.add(new Point(i,j));
                }
            }
        }
        return neighbors;
    }

    public int at(int[][] matrix) {
        return matrix[x][y];
    }

    public void set(int[][] matrix, int value) {
        matrix[x][y] = value;
    }
}
