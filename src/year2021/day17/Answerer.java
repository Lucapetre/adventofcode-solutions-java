package year2021.day17;

class Answerer {

    private final ProbeThrower probeThrower;

    public Answerer(String input) {
        this.probeThrower = new ProbeThrower(input);
    }

    public String answerPart1() {
        return String.valueOf(probeThrower.getMaxHeight());
    }

    public String answerPart2() {
        return String.valueOf(probeThrower.getNumberOfPoints());
    }

    @Override
    public String toString() {
        return "Part 1:\n" +
                "Max height: " + answerPart1() + "\n" +
                "Part 2:\n" +
                "Total valid initial velocities: " + answerPart2();
    }
}
