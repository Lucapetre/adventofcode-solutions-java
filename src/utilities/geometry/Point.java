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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public int at(int[][] matrix) {
        return matrix[x][y];
    }

    public void set(int[][] matrix, int value) {
        matrix[x][y] = value;
    }

    public boolean inBounds(int width,int height) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }
}
