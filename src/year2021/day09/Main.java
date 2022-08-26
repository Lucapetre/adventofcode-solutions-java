package year2021.day09;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        Scanner inputReader = null;
        try {
            inputReader = new Scanner(Paths.get("input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        List<String> input = new ArrayList<>();
        while (inputReader.hasNextLine()) {
            input.add(inputReader.nextLine());
        }

        RiskCalculator riskCalculator = new RiskCalculator(input);
        riskCalculator.determineRiskLevelAndBasins();
        System.out.println(riskCalculator);
    }

}
