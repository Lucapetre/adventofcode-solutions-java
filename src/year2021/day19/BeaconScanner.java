package year2021.day19;

import utilities.LineProcessor;
import utilities.geometry.Point3D;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class BeaconScanner {

    private final List<Point3D> beacons;
    private boolean aligned;

    private Point3D offset;

    private BeaconScanner() {
        this.beacons = new ArrayList<>();
        this.aligned = false;
    }

    public BeaconScanner(List<String> input) {
        this();
        for(String line:input) {
            beacons.add(new Point3D(LineProcessor.getNumbersFromInputString(line)));
        }
    }

    private BeaconScanner(BeaconScanner scanner, int position) {
        this();
        for(Point3D beacon:scanner.beacons) {
            beacons.add(beacon.rotateEverywhere(position));
        }
    }

    public BeaconScanner rotateScanner(int position) {
        return new BeaconScanner(this,position);
    }

    public List<Point3D> getBeacons() {
        return beacons;
    }

    public boolean isAligned() {
        return aligned;
    }

    public Point3D getOffset() {
        return offset;
    }

    public void setAligned() {
        this.aligned = true;
    }

    public void setOffset(Point3D offset) {
        this.offset = offset;
    }

    public void addOffsetPointsToSet(Set<Point3D> set) {
        for(Point3D point:beacons) {
            set.add(point.subtract(offset));
        }
    }
}
