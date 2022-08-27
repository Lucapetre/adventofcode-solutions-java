package year2021.day22;

import utilities.ArrayUtils;
import utilities.LineProcessor;
import utilities.geometry.Cuboid;
import utilities.geometry.Point3D;

import java.util.ArrayList;
import java.util.List;

class SmallCubeGrid {

    private final int[][][] cube;

    public SmallCubeGrid(List<String> input) {
        this.cube = new int[101][101][101];
        for (int i = 0; i < 101; i++) {
            ArrayUtils.fillMatrix(cube[i], 0);
        }

        for(String line:input) {
            if (line.charAt(1) == 'n') { //on
                line = line.substring(5);
                List<Point3D> points = getPointsInCoordinates(line);
                for(Point3D point:points) {
                    point.set(cube,1);
                }
            } else {
                line = line.substring(6);
                List<Point3D> points = getPointsInCoordinates(line);
                for(Point3D point:points) {
                    point.set(cube,0);
                }
            }
        }
    }

    private List<Point3D> getPointsInCoordinates(String line) {
        List<Integer> coordinates = LineProcessor.getNumbersFromInputString(line,"(\\.\\.|,y=|,z=)");
        if(coordinates.stream().anyMatch(x -> Math.abs(x) > 50)) {
            return new ArrayList<>();
        }
        Cuboid cuboid = new Cuboid(coordinates.get(0) + 50,coordinates.get(2) + 50,coordinates.get(4) + 50,
                coordinates.get(1) + 50,coordinates.get(3) + 50,coordinates.get(5) + 50);
        return cuboid.pointsInside();
    }

    public int countOnCubes() {
        int count = 0;
        for (int x = 0; x < 101; x++) {
            for (int y = 0; y < 101; y++) {
                for (int z = 0; z < 101; z++) {
                    count += cube[x][y][z];
                }
            }
        }
        return count;
    }

}
