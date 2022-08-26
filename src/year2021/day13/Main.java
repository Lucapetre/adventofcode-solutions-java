package year2021.day13;

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

        PaperFolder paperFolder = new PaperFolder(input);
        paperFolder.foldOnce();
        System.out.println(paperFolder);
        paperFolder.allFolds();
        System.out.println(paperFolder);

    }
}
