package year2021.day20;

import utilities.ArrayUtils;
import utilities.BaseManipulator;
import utilities.geometry.Point;

import java.util.Arrays;
import java.util.List;

class TrenchImage {

    private final int[] enhancingAlgorithm;
    private int[][] grid;
    private int size;

    public TrenchImage(List<String> input) {

        String algorithm = input.get(0);
        input.remove(0);
        input.remove(0);

        enhancingAlgorithm = new int[512];
        Arrays.fill(enhancingAlgorithm,0);
        for (int i = 0; i < algorithm.length(); i++) {
            if(algorithm.charAt(i) == '#') {
                enhancingAlgorithm[i] = 1;
            }
        }

        this.size = Math.max(input.size(),input.get(0).length()) + 8;
        this.grid = new int[size][size];
        ArrayUtils.fillMatrix(grid,0);


        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            for (int j = 0; j < line.length(); j++) {
                if(line.charAt(j) == '#') {
                    grid[i + 3][j + 3] = 1;
                }
            }
        }

    }

    private int getPointResult(Point position) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = position.x() - 1; i <= position.x() + 1; i++) {
            for (int j = position.y() - 1; j <= position.y() + 1 ; j++) {
                stringBuilder.append(grid[i][j]);
            }
        }
        return enhancingAlgorithm[(int) BaseManipulator.convertBaseToDecimal(stringBuilder.toString(),2)];
    }

    private void enhanceImage() {

        int newSize = size + 4;
        int[][] newGrid = new int[newSize][newSize];

        for (int i = 1; i < size - 1; i++) {
            for (int j = 1; j < size - 1; j++) {
                newGrid[i + 2][j + 2] = getPointResult(new Point(i,j));
            }
        }

        this.grid = newGrid;
        this.size = newSize;
        int backgroundLight = grid[3][3];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = grid[j][i] = grid[i][size - 1 - j] = grid[size - 1 - j][i] = backgroundLight;
            }
        }
    }

    public void enhanceNTimes(int number) {
        for (int i = 0; i < number; i++) {
            enhanceImage();
        }
    }

    public int numberOfLightPoints() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                count += grid[i][j];
            }
        }
        return count;
    }

}
