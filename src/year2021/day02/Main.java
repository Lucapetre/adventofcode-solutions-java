package year2021.day02;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        Scanner fileReader;
        try {
            fileReader = new Scanner(Paths.get("input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Submarine submarine1 = new Submarine();
        Submarine submarine2 = new Submarine();

        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();

            submarine1.processInstructionPart1(line);
            submarine2.processInstructionPart2(line);
        }

        System.out.println("Part 1:\n" + submarine1);
        System.out.println("Part 2:\n" + submarine2);
    }

}
