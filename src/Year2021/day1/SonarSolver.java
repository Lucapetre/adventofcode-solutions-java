package Year2021.day1;

import java.util.ArrayList;
import java.util.Scanner;

public class SonarSolver {

    private final ArrayList<Integer> depths;

    public SonarSolver() {
        this.depths = new ArrayList<>();
    }

    public void readFromFile(Scanner scanner){
        while(scanner.hasNext()){
            depths.add(Integer.parseInt(scanner.nextLine()));
        }
    }

    public int count() {
        int result = 0;
        for (int i = 1; i < depths.size(); i++) {
            if (depths.get(i) > depths.get(i-1)) {
                result++;
            }
        }
        return result;
    }

    public int countSliding() { /// a1+a2+a3 < a2+a3+a4 <=> a1 < a4
        int result = 0;
        for (int i = 3; i < depths.size(); i++) {
            if (depths.get(i) > depths.get(i-3)) {
                result++;
            }
        }
        return result;
    }

}
