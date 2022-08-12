package year2021.day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> input = null;
        try {
            input = Files.readAllLines(Paths.get("input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        String initialPolymer = input.get(0);
        input.remove(0);
        input.remove(0);

        PolymerMapper.instanceMaps(input);

        PolymerCalculator polymerCalculator = new PolymerCalculator(initialPolymer);
        polymerCalculator.doNSteps(10);
        System.out.println(polymerCalculator);
        polymerCalculator.doNSteps(30);
        System.out.println(polymerCalculator);

    }
}
