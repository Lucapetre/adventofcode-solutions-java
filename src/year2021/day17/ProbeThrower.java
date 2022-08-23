package year2021.day17;

import utilities.geometry.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProbeThrower {

    private final List<Point> validVelocities;
    private final Point leftDownTarget;
    private final Point rightUpTarget;

    public ProbeThrower(String targetLine) {
        String[] split = targetLine.split("(target area: x=|\\.\\.|, y=)");
        this.leftDownTarget = new Point(Integer.parseInt(split[1]),Integer.parseInt(split[3]));
        this.rightUpTarget = new Point(Integer.parseInt(split[2]),Integer.parseInt(split[4]));

        this.validVelocities = new ArrayList<>();

        int maxY = Math.max(Math.abs(leftDownTarget.y()),Math.abs(rightUpTarget.y()));
        int maxX = rightUpTarget.x();

        for (int x = 0; x <= maxX; x++) {
            for (int y = -maxY; y <= maxY; y++) {
                testVelocity(x,y);
            }
        }
    }


    private void testVelocity(int xSpeed,int ySpeed) {
        Point initialSpeed = new Point(xSpeed,ySpeed);
        int x = 0;
        int y = 0;
        while (x <= rightUpTarget.x() && y >= leftDownTarget.y()) { // the probe can still reach the target area
            x += xSpeed;
            y += ySpeed;

            if(xSpeed != 0) {
                xSpeed--;
            }
            ySpeed--;

            if(new Point(x,y).insideRectangleFromPoints(leftDownTarget,rightUpTarget)) {
                validVelocities.add(initialSpeed);
                return;
            }
        }
    }

    public int getMaxHeight() {
        return validVelocities.stream()
                .mapToInt(Point::y)
                .filter(y -> y >= 0)
                .map(y -> y * (y + 1) / 2)
                .reduce(0,Math::max);
    }

    public int getNumberOfPoints() {
        return validVelocities.size();
    }
}
