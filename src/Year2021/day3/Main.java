package Year2021.day3;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) {

        Scanner inputReader;
        try {
            inputReader = new Scanner(Paths.get("input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ArrayList<String> input = new ArrayList<>();
        String line = inputReader.nextLine();
        input.add(line);
        int size = line.length();

        PowerCalculator powerCalculator = new PowerCalculator(size);


        while(inputReader.hasNextLine()) {
            String inputLine = inputReader.nextLine();
            input.add(inputLine);
        }

        for (String inputLine: input) {
            powerCalculator.processLine(inputLine);
        }

        powerCalculator.getGammaAndEpsilonBits();
        powerCalculator.calculateGammaAndEpsilon();
        System.out.println(powerCalculator);

        LifeSupportRater lifeSupportRater = new LifeSupportRater(size, input.size(),input);
        lifeSupportRater.allSteps();
        lifeSupportRater.getO2AndCo2();
        System.out.println(lifeSupportRater);
    }
}
