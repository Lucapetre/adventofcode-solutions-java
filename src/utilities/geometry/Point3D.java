package utilities.geometry;

import java.util.List;
import java.util.Objects;

public record Point3D (int x, int y, int z) {

    public Point3D (List<Integer> list) {
        this(list.get(0),list.get(1), list.get(2));
        if(list.size() != 3) {
            throw new IllegalArgumentException("list should have 3 elements, has: " + list.size());
        }
    }

    public Point3D add(Point3D point) {
        return new Point3D(this.x + point.x, this.y + point.y, this.z + point.z);
    }

    public Point3D opposite() {
        return new Point3D(-this.x, -this.y, -this.z);
    }

    public Point3D subtract(Point3D point) {
        return this.add(point.opposite());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point3D point3D)) return false;

        if (x != point3D.x) return false;
        if (y != point3D.y) return false;
        return z == point3D.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y,z);
    }

    private Point3D setFaceUp(int face) { // rotate to set z to: 0, 1 -> +-z 2,3 -> +-y 4,5 -> +-x
        return switch (face) {
            case 0 -> new Point3D( x, y, z);
            case 1 -> new Point3D( x,-y,-z);
            case 2 -> new Point3D( x,-z, y);
            case 3 -> new Point3D( x, z,-y);
            case 4 -> new Point3D(-z, y, x);
            case 5 -> new Point3D(-z,-y,-x);
            default -> throw new IllegalArgumentException("Face should be between 0 and 5, was: " + face);
        };
    }

    private Point3D rotateAroundUp(int angle) {
        // angle is 0 -> 3, corresponds to 0, 90, 180 and 270 rotation around the facing direction (z)
        return switch (angle) {
            case 0 -> new Point3D( x, y, z);
            case 1 -> //noinspection SuspiciousNameCombination -they should be swapped intellij
                      new Point3D(-y, x, z);
            case 2 -> //noinspection SuspiciousNameCombination
                      new Point3D( y,-x, z);
            case 3 -> new Point3D(-x,-y, z);
            default -> throw new IllegalArgumentException("Angle should be between 0 and 3, was: " + angle);
        };
    }

    public Point3D rotateEverywhere(int position) {
        // merges previous 2 methods to get every position possible
        // position is from 0 -> 23

        return this.setFaceUp(position % 6).rotateAroundUp(position / 6);
    }

    public int manhattanDistance(Point3D point) {
        return Math.abs(this.x  - point.x) + Math.abs(this.y  - point.y) + Math.abs(this.z  - point.z);
    }

    @Override
    public String toString() {
        return x + "," + y + "," + z;
    }
}
