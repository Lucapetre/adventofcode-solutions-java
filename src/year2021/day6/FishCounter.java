package year2021.day6;

import utilities.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class FishCounter {

    private final long[][] fish; // fish[i][j] = number of fish with j counter at day i and i is mod 2
    private int daysPassed;
    private long totalFish;
    private int part;

    public FishCounter(List<Integer> fish) {
        this.fish = new long[2][9];
        ArrayUtils.fillMatrix(this.fish,0);
        this.daysPassed = 0;
        this.totalFish = 0;
        this.part = 1;


        for(Integer fishNumber: fish) {
            this.fish[daysPassed][fishNumber]++;
            this.totalFish++;
        }
    }

    private void passDay() {
        this.daysPassed++;
        Arrays.fill(fish[daysPassed % 2],0);
        // decrease counter by 1
        IntStream.range(0, 8).forEach(i -> fish[daysPassed % 2][i] = fish[(daysPassed - 1) % 2][i + 1]);
        fish[daysPassed % 2][8] += fish[(daysPassed - 1) % 2][0]; // replicate fish
        fish[daysPassed % 2][6] += fish[(daysPassed - 1) % 2][0]; // change counter
        totalFish += fish[(daysPassed - 1) % 2][0];
    }

    public void passNDays(int number) {
        for (int i = 0; i < number; i++) {
            passDay();
        }
    }

    public String toString() {
        return "Part " + (part++) + ":\n" +
                "Total fish: " + totalFish;
    }
}
