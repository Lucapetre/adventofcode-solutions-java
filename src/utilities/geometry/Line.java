package utilities.geometry;

public class Line {

    private Point start;
    private Point end;

    private void assign(int xStart, int yStart, int xEnd, int yEnd) {
        this.start = new Point(xStart,yStart);
        this.end = new Point(xEnd, yEnd);
    }

    public Line(String line) {
        // the string must contain four integers separated by spaces, commas or " -> "
        // see day5

        String[] numbers = line.split(" -> |[ ,]");
        if(numbers.length != 4) {
            System.out.println(line);
            throw new IllegalArgumentException("4 integers");
        }

        int[] intNumbers = new int[4];
        for (int i = 0; i < 4; i++) {
            intNumbers[i] = Integer.parseInt(numbers[i]);
        }

        assign(intNumbers[0],intNumbers[1],intNumbers[2],intNumbers[3]);
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public boolean isHorizontal() {
        return start.getX() == end.getX();
    }

    public boolean isVertical() {
        return start.getY() == end.getY();
    }

    public boolean isDiagonal() {
        return Math.abs(start.getY() - end.getY()) == Math.abs(end.getX() - start.getX()) ;
    }

    public boolean isHorizontalOrVertical() {
        return isHorizontal() || isVertical();
    }

    public void swapEnds() {
        Point swapper = start;
        start = end;
        end = swapper;
    }
}
