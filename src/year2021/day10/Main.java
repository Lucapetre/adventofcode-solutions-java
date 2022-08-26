package year2021.day10;

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

        SyntaxChecker syntaxChecker = new SyntaxChecker(input);
        System.out.println(syntaxChecker);
    }
}
