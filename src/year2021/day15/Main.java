package year2021.day15;

import utilities.geometry.Point;

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

        ChitonGrid chitonGrid = new ChitonGrid(input,5);
        chitonGrid.calculateMinimumRiskLevel(new Point(0,0),false);
        System.out.println(chitonGrid);
        chitonGrid.calculateMinimumRiskLevel(new Point(0,0),true);
        System.out.println(chitonGrid);

    }

}
