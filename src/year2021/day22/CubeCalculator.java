package year2021.day22;

import utilities.LineProcessor;
import utilities.Pair;
import utilities.geometry.Cuboid;

import java.util.ArrayList;
import java.util.List;

class CubeCalculator {
    
    private final List<Pair<Integer, Cuboid>> signedCuboids;
    
    public CubeCalculator(List<String> input) {

        this.signedCuboids = new ArrayList<>();
        for(String line:input) {
            if (line.charAt(1) == 'n') { //on
                line = line.substring(5);
                Cuboid cuboid = createAndAddCuboid(line);
                signedCuboids.add(new Pair<>(1,cuboid));
                
            } else {
                line = line.substring(6);
                createAndAddCuboid(line);
            }
        }
        
    }

    private Cuboid createAndAddCuboid(String line) {
        List<Integer> coordinates = LineProcessor.getNumbersFromInputString(line,"(\\.\\.|,y=|,z=)");
        Cuboid cuboid = new Cuboid(coordinates.get(0),coordinates.get(2),coordinates.get(4),
                coordinates.get(1),coordinates.get(3),coordinates.get(5));

        List<Pair<Integer,Cuboid>> cubesToAdd = new ArrayList<>();

        for (Pair<Integer,Cuboid> signedCuboid : signedCuboids) {
            Cuboid listCuboid = signedCuboid.second();
            int sign = signedCuboid.first();
            Cuboid intersection = cuboid.intersection(listCuboid);
            if (intersection != null) {
               cubesToAdd.add(new Pair<>(-sign,intersection));
            }
        }
        signedCuboids.addAll(cubesToAdd);
        return cuboid;
    }

    public long totalVolume() {
        long result = 0;
        for (Pair<Integer,Cuboid> signedCuboid : signedCuboids) {
            result = result + (long) signedCuboid.first() * signedCuboid.second().specialVolume();
        }
        return result;
    }

}
