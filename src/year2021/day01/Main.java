package year2021.day01;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        try(Scanner scanner = new Scanner(Paths.get("input.txt"))) {

            SonarSolver solver = new SonarSolver();
            solver.readFromFile(scanner);
            System.out.println(solver.count());
            System.out.println(solver.countSliding());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
