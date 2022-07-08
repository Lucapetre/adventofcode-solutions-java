package Year2021.day1;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try(Scanner scanner = new Scanner(Paths.get("day1input.txt"))) {

            SonarSolver solver = new SonarSolver();
            solver.readFromFile(scanner);
            System.out.println(solver.count());
            System.out.println(solver.countSliding());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
