package year2021.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class Main {

    public static void main(String[] args) {

        List<String> input = null;
        try {
            input = Files.readAllLines(Paths.get("input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        OctopusGrid octopusGrid = new OctopusGrid(input);
        octopusGrid.doNSteps(100);
        System.out.println(octopusGrid);
        octopusGrid.doStepsUntilSync();
        System.out.println(octopusGrid);

    }
}
