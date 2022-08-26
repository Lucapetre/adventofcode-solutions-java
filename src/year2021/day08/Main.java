package year2021.day08;

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

        List<String> inputStrings = new ArrayList<>();

        while(inputReader.hasNextLine()) {
            inputStrings.add(inputReader.nextLine());
        }
        SegmentCounter segmentCounter = new SegmentCounter(inputStrings);
        segmentCounter.countEasyDigits();
        segmentCounter.determineSum();
        System.out.println(segmentCounter);
    }

}
