package year2021.day13;

import utilities.geometry.Point;

import java.util.HashSet;
import java.util.Set;

public class FoldInstruction {

    private final char foldAcross;
    private final int coordinate;


    public FoldInstruction(String instruction) {
        String[] parts = instruction.split("[ =]");
        if(parts.length != 4) {
            throw new IllegalArgumentException("Instruction must have four parts. Instruction was: " + instruction +
                    " and had " + parts.length + " parts");
        }

        this.foldAcross = parts[2].charAt(0);
        this.coordinate = Integer.parseInt(parts[3]);
    }

    public Set<Point> foldPointSet(Set<Point> pointSet) {
        Set<Point> newSet = new HashSet<>();

        if(foldAcross == 'x') {
            for (Point point : pointSet) {
                if (point.x() <= coordinate) {
                    newSet.add(point);
                } else {
                    newSet.add(new Point(2 * coordinate - point.x(), point.y()));
                }
            }
        } else if (foldAcross == 'y') {
            for (Point point : pointSet) {
                if (point.y() <= coordinate) {
                    newSet.add(point);
                } else {
                    newSet.add(new Point(point.x(), 2 * coordinate - point.y()));
                }
            }
        }

        return newSet;
    }

}
