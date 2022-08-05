package utilities.geometry;

import java.util.ArrayList;
import java.util.List;

public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Point> getAdjacentPoints() {
        List<Point> neighbors = new ArrayList<>();
        neighbors.add(new Point(x-1,y));
        neighbors.add(new Point(x+1,y));
        neighbors.add(new Point(x,y-1));
        neighbors.add(new Point(x,y+1));
        return neighbors;
    }

    public int at(int[][] matrix) {
        return matrix[x][y];
    }

    public void set(int[][] matrix,int value) {
        matrix[x][y] = value;
    }
}
