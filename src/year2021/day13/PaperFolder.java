package year2021.day13;

import utilities.geometry.Point;

import java.util.*;

class PaperFolder {

    private Set<Point> pointSet;
    private final List<FoldInstruction> foldInstructions;

    private int part;

    private int folds;

    public PaperFolder(List<String> input) {
        this.pointSet = new HashSet<>();
        this.foldInstructions = new ArrayList<>();

        int mode = 1;
        for(String line:input) {
            if(line.equals("")) {
                mode = 2;
            } else {
                processLine(line,mode);
            }
        }

        this.part = 0;
        this.folds = 0;
    }

    private void processLine(String line,int mode) {
        if(mode == 1) {
            String[] parts = line.split(",");
            pointSet.add(new Point(Integer.parseInt(parts[0]),Integer.parseInt(parts[1])));
        } else if(mode == 2) {
            foldInstructions.add(new FoldInstruction(line));
        }
    }

    public void foldOnce() {
        FoldInstruction firstFold = foldInstructions.get(0);
        foldInstructions.remove(0);
        pointSet = firstFold.foldPointSet(pointSet);
        folds++;
    }

    public void allFolds() {
        while (!foldInstructions.isEmpty()) {
            foldOnce();
        }
    }

    private String letters() {
        int maxY = 0;
        int maxX = 0;
        for(Point point:pointSet) {
            maxX = Math.max(point.x(),maxX);
            maxY = Math.max(point.y(),maxY);
        }
        char[][] grid = new char[maxY+1][maxX+1];
        for (int i = 0; i < maxY; i++) {
            Arrays.fill(grid[i],'.');
        }
        for(Point point:pointSet) {
            grid[point.y()][point.x()] = '#';
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < maxY; i++) {
            stringBuilder.append(grid[i]);
            stringBuilder.append("\n");
        }
        stringBuilder.append(grid[maxY]);
        return stringBuilder.toString();
    }



    @Override
    public String toString() {
        if(part == 0) {
            return "Part " + (++part) + "\n" +
                    "Points after " + folds + " fold(s): " + pointSet.size();
        }
        return "Part 2:\n" +
                letters();
    }
}
