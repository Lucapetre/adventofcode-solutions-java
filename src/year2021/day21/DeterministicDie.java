package year2021.day21;

class DeterministicDie {

    protected int lastRolled;
    private int timesRolled;

    protected DeterministicDie() {
        timesRolled = 0;
        lastRolled = 0;
    }

    protected int getRollNumber() {
        lastRolled++;
        if(lastRolled > 100) {
            lastRolled %= 100;
        }
        return lastRolled;
    }

    private int roll() {
        timesRolled++;
        return getRollNumber();
    }

    public int rollThreeTimes() {
        return roll() + roll() + roll();
    }

    public int getTimesRolled() {
        return timesRolled;
    }

}
