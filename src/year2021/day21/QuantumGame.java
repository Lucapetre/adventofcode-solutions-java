package year2021.day21;

import utilities.Pair;

import java.util.List;

class QuantumGame {

    Player player1;
    Player player2;
    int turn; // 1 - player 1, 2 - player 2

    public QuantumGame(List<String> input) {
        player1 = new Player(input.get(0));
        player2 = new Player(input.get(1));
        this.turn = 1;
    }

    public QuantumGame(QuantumGame game) {
        this.player1 = new Player(game.player1);
        this.player2 = new Player(game.player2);
        this.turn = 3 - game.turn; // switches turn
    }

    public Pair<Long,Long> p1p2WinsInUniverse() {
        if(player1.win(21)) {
            return new Pair<>(1L,0L);
        }
        if(player2.win(21)) {
            return new Pair<>(0L,1L);
        }

        int[] probabilities = {0,0,0,1,3,6,7,6,3,1};
        long p1Wins = 0L;
        long p2Wins = 0L;
        for (int i = 3; i < probabilities.length; i++) {
            QuantumGame game = new QuantumGame(this);
            if(turn == 1) {
                game.player1.advance(i);
            } else {
                game.player2.advance(i);
            }

            Pair<Long,Long> outcome = game.p1p2WinsInUniverse();
            p1Wins += outcome.first() * probabilities[i];
            p2Wins += outcome.second() * probabilities[i];
        }

        return new Pair<>(p1Wins,p2Wins);
    }

}
