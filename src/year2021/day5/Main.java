package year2021.day5;

import utilities.geometry.Line;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner inputReader;
        try {
            inputReader = new Scanner(Paths.get("input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Line> lines = new ArrayList<>();
        while(inputReader.hasNextLine()) {
            lines.add(new Line(inputReader.nextLine()));
        }

        OceanFloorGrid oceanFloorGrid = new OceanFloorGrid();
        oceanFloorGrid.markHorizontalOrVerticalLinesFromList(lines);
        oceanFloorGrid.countOverlappingPoints(1);
        System.out.println(oceanFloorGrid);

        oceanFloorGrid.markDiagonalLinesFromList(lines);
        oceanFloorGrid.countOverlappingPoints(2);
        System.out.println(oceanFloorGrid);
    }
}
