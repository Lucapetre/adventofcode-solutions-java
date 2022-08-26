package year2021.day21;

class Player {

    private int position;
    private int score;

    public Player(String playerStart) {

        String[] parts = playerStart.split(": ");
        this.position = Integer.parseInt(parts[1]);
        this.score = 0;
    }

    public Player(Player player) {
        this.position = player.position;
        this.score = player.score;
    }

    public void advance(int value) {
        this.position = (this.position + value - 1) % 10 + 1;
        this.score += this.position;
    }

    public void rollDie(DeterministicDie die) {
        int move = die.rollThreeTimes();
        advance(move);
    }

    public boolean win(int score) {
        return this.score >= score;
    }

    public int getScore() {
        return score;
    }
}
