package year2021.day07;

import utilities.LineProcessor;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner inputReader = null;
        try {
            inputReader = new Scanner(Paths.get("input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        String inputLine = inputReader.nextLine();
        List<Integer> crabList = LineProcessor.getNumbersFromInputString(inputLine);

        CrabAligner crabAligner = new CrabAligner(crabList);
        crabAligner.calculateMinDistancePart1();
        System.out.println(crabAligner);

        crabAligner.calculateMinDistancePart2();
        System.out.println(crabAligner);
    }

}
