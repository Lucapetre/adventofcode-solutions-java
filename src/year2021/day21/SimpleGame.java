package year2021.day21;

import java.util.List;

class SimpleGame {

    Player player1;
    Player player2;
    DeterministicDie die;

    public SimpleGame(List<String> input) {
        player1 = new Player(input.get(0));
        player2 = new Player(input.get(1));
        die = new DeterministicDie();
    }

    public void play() {
        while (true) {
            player1.rollDie(die);
            if(player1.win(1000)) {
                break;
            }

            player2.rollDie(die);
            if(player2.win(1000)) {
                break;
            }
        }
    }

    public int getProductDieTimesScore() {
        return Math.min(player1.getScore(), player2.getScore()) * die.getTimesRolled();
    }

}
