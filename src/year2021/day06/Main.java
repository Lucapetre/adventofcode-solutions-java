package year2021.day06;

import utilities.LineProcessor;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


class Main {

    public static void main(String[] args) {

        Scanner inputReader;
        try {
            inputReader = new Scanner(Paths.get("input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String line = inputReader.nextLine();
        List<Integer> inputList = LineProcessor.getNumbersFromInputString(line, "[ ,]");

        FishCounter fishCounter = new FishCounter(inputList);
        fishCounter.passNDays(80);
        System.out.println(fishCounter);
        fishCounter.passNDays(256 - 80);
        System.out.println(fishCounter);
    }

}
