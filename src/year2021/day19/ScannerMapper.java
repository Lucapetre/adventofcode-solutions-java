package year2021.day19;

import utilities.geometry.Point3D;

import java.util.*;

class ScannerMapper {

    private final List<BeaconScanner> scanners;
    private final boolean[] triedAlignmentWithEveryone;

    public ScannerMapper(List<String> input) {

        this.scanners = new ArrayList<>();
        int i = 1;
        List<String> scannerBeaconList = new ArrayList<>();
        while(i < input.size()) {
            if (input.get(i).equals("")) {
                i+=2;
                scanners.add(new BeaconScanner(scannerBeaconList));
                scannerBeaconList = new ArrayList<>();
            } else {
                scannerBeaconList.add(input.get(i));
                i++;
            }
        }

        scanners.add(new BeaconScanner(scannerBeaconList));

        triedAlignmentWithEveryone = new boolean[scanners.size()];
        Arrays.fill(triedAlignmentWithEveryone,false);

        scanners.get(0).setOffset(new Point3D(0,0,0));
        scanners.get(0).setAligned();
    }

    private boolean everyoneAligned() {
        for(BeaconScanner scanner:scanners) {
            if(!scanner.isAligned()) {
                return false;
            }
        }
        return true;
    }

    private boolean canAlign(int i,int j) {
        BeaconScanner alignedScanner = scanners.get(i);
        BeaconScanner notAlignedScanner = scanners.get(j);

        if(i == j) {
            return false;
        }
        if(!alignedScanner.isAligned()) {
            return false;
        }
        if(notAlignedScanner.isAligned()) {
            return false;
        }
        return !triedAlignmentWithEveryone[i];
    }

    private boolean verifyOffset(List<Point3D> alignedBeacons, List<Point3D> notAlignedBeacons, Point3D offset) {
        int count = 0;
        for(Point3D point:alignedBeacons) {
            if(notAlignedBeacons.contains(point.add(offset))){
                count++;
            }
        }
        return count >= 12;
    }

    private void tryAlign(int i,int j) {

        BeaconScanner notAlignedScanner = scanners.get(j);
        BeaconScanner rotatedScanner;

        List<Point3D> alignedBeacons = scanners.get(i).getBeacons();
        List<Point3D> notAlignedBeacons;

        for (int k = 0; k < 24; k++) {
            rotatedScanner = notAlignedScanner.rotateScanner(k);
            notAlignedBeacons = rotatedScanner.getBeacons();

            for (int l = 0; l < alignedBeacons.size(); l++) {
                for (int m = 0; m < notAlignedBeacons.size(); m++) {
                    Point3D offset = notAlignedBeacons.get(m).subtract(alignedBeacons.get(l));
                    if(verifyOffset(alignedBeacons,notAlignedBeacons,offset))
                    {
                        scanners.remove(j);
                        scanners.add(j,rotatedScanner);
                        rotatedScanner.setAligned();
                        rotatedScanner.setOffset(scanners.get(i).getOffset().add(offset));
                        return;
                    }
                }
            }
        }
    }

    public void alignEveryone() {
        while(!everyoneAligned()) {
            for (int i = 0; i < scanners.size(); i++) {
                for (int j = 0; j < scanners.size(); j++) {
                    if(canAlign(i,j)) {
                        tryAlign(i,j);
                    }
                }
                if(scanners.get(i).isAligned()) {
                    triedAlignmentWithEveryone[i] = true;
                }
            }
        }
    }

    public Set<Point3D> absolutePoints() {
        Set<Point3D> absolutePoints = new HashSet<>();
        for (BeaconScanner scanner:scanners) {
            scanner.addOffsetPointsToSet(absolutePoints);
        }
        return absolutePoints;
    }

    public int maxDistance() {
        int max = 0;
        for (int i = 0; i < scanners.size(); i++) {
            for (int j = i + 1; j < scanners.size(); j++) {
                max = Math.max(max,scanners.get(i).getOffset().manhattanDistance(scanners.get(j).getOffset()));
            }
        }
        return max;
    }

}
