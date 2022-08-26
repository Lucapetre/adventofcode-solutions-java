package year2021.day04;

import java.util.List;

class BingoBoard {

    private boolean bingo;
    private boolean calledBingo;
    private int score;
    private final int[][] board;
    private final int[][] markedNumbers;

    public BingoBoard(List<Integer> boardNumbers) {

        if (boardNumbers.size() != 25) {
            throw new IllegalArgumentException("Creating a bingo board requires 25 numbers (5*5)");
        }

        this.bingo = false;
        this.calledBingo = false;

        this.board = new int[5][5];
        this.markedNumbers = new int[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = boardNumbers.get(i * 5 + j);
                markedNumbers[i][j] = 0;
            }
        }
    }

    public void mark(int number) {

        if(bingo) {
            return;
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(board[i][j] == number) {
                    markedNumbers[i][j] = 1;
                }
            }
        }
    }

    public void checkBingo () {
        if(bingo) {
            return;
        }

        for (int i = 0; i < 5; i++) {
            boolean okLine = true;
            boolean okColumn = true;
            for (int j = 0; j < 5; j++) {
                if(markedNumbers[i][j] == 0) {
                    okLine = false;
                }
                if(markedNumbers[j][i] == 0) {
                    okColumn = false;
                }
            }
            if(okColumn || okLine) {
                bingo = true;
                return;
            }
        }
    }

    private int sumUnmarked() {

        int sum = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(markedNumbers[i][j] == 0) {
                    sum += board[i][j];
                }
            }
        }
        return sum;
    }

    public boolean bingo(int number) {
        if(!calledBingo && bingo) {
            calledBingo = true;
            score = sumUnmarked() * number;
            return true;
        }
        return false;
    }

    public int getScore() {
        return score;
    }
}
