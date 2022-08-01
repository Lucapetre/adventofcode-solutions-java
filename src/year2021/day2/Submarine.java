package year2021.day2;

public class Submarine {

    private int depth;
    private int horizontalPosition;

    private int aim;

    public Submarine() {
        depth = 0;
        horizontalPosition = 0;
        aim = 0;
    }

    public void processInstructionPart1 (String line) {
        String[] parts = line.split(" ");

        String instruction = parts[0];
        int amount = Integer.parseInt(parts[1]);

        switch (instruction) {
            case "forward" -> this.horizontalPosition += amount;
            case "down" -> this.depth += amount;
            case "up" -> this.depth -= amount;
        }
    }

    public void processInstructionPart2 (String line) {
        String[] parts = line.split(" ");

        String instruction = parts[0];
        int amount = Integer.parseInt(parts[1]);

        switch (instruction) {
            case "forward" -> {
                this.horizontalPosition += amount;
                this.depth += this.aim * amount;
            }
            case "down" -> this.aim += amount;
            case "up" -> this.aim -= amount;
        }
    }

    public String toString() {
        return "Depth: " + depth + "\n" +
                "Horizontal position: " + horizontalPosition + "\n" +
                "Product: " + (depth * horizontalPosition);
    }

}
