package utilities.geometry;

import java.util.ArrayList;
import java.util.List;

public class Cuboid {

    private final Point3D start;
    private final Point3D end;

    public Cuboid(Point3D start,Point3D end) {
        this.start = start;
        this.end = end;
    }

    public Cuboid(int xStart, int yStart, int zStart, int xEnd, int yEnd, int zEnd) {
        this(new Point3D(xStart,yStart,zStart), new Point3D(xEnd,yEnd,zEnd));
    }

    public List<Point3D> pointsInside() {
        List<Point3D> pointList = new ArrayList<>();
        for (int x = start.x(); x <= end.x() ; x++) {
            for (int y = start.y(); y <= end.y() ; y++) {
                for (int z = start.z(); z <= end.z() ; z++) {
                    pointList.add(new Point3D(x,y,z));
                }
            }
        }
        return pointList;
    }

    public long specialVolume() {
        return (long) (end.x() - start.x() + 1) * (long) (end.y() - start.y() + 1) * (long) (end.z() - start.z() + 1);
    }
    
    public Cuboid intersection(Cuboid other) {
        int xStart = Math.max(start.x(),other.start.x());
        int xEnd = Math.min(end.x(),other.end.x());
        if(xStart > xEnd) {
            return null;
        }
        int yStart = Math.max(start.y(),other.start.y());
        int yEnd = Math.min(end.y(),other.end.y());
        if(yStart > yEnd) {
            return null;
        }
        int zStart = Math.max(start.z(),other.start.z());
        int zEnd = Math.min(end.z(),other.end.z());
        if(zStart > zEnd) {
            return null;
        }
        return new Cuboid(xStart,yStart,zStart,xEnd,yEnd,zEnd);
    }
    
}
