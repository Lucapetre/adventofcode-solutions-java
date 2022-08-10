package year2021.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> input = null;
        try {
            input = Files.readAllLines(Paths.get("input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        CaveMapper caveMapper =new CaveMapper(input);
        caveMapper.findTotalPaths();
        System.out.println(caveMapper);
        caveMapper.findTotalPaths();
        System.out.println(caveMapper);
    }

}
