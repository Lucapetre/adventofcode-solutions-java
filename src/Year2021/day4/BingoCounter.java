package Year2021.day4;

import java.util.ArrayList;
import java.util.List;

public class BingoCounter {

    private final List<Integer> numbersCalled;
    private final List<BingoBoard> bingoBoards;

    private final int totalBoards;
    private int boardsWon;
    private int firstScore;
    private int lastScore;

    public BingoCounter(List<Integer> numbersCalled, List<BingoBoard> bingoBoards) {
        this.numbersCalled = numbersCalled;
        this.bingoBoards = bingoBoards;

        this.totalBoards = bingoBoards.size();
        this.boardsWon = 0;
    }

    private void callNumber(int number) {

        if(boardsWon == totalBoards) {
            return;
        }

        List<BingoBoard> bingoBoardsToBeRemoved = new ArrayList<>();

        for(BingoBoard bingoBoard: bingoBoards) {
            bingoBoard.mark(number);
            bingoBoard.checkBingo();
            if(bingoBoard.bingo(number)) {
                boardsWon++;
                if(boardsWon == 1) {
                    firstScore = bingoBoard.getScore();
                }
                if(boardsWon == totalBoards) {
                    lastScore = bingoBoard.getScore();
                    return;
                }
                bingoBoardsToBeRemoved.add(bingoBoard);
            }
        }

        for(BingoBoard bingoBoard: bingoBoardsToBeRemoved) {
            bingoBoards.remove(bingoBoard);
        }

    }

    public void callAllNumbers() {

        for(Integer number: numbersCalled) {
            callNumber(number);
        }
    }

    public String toString () {
        return "Part1: " + firstScore + "\n" +
                "Part2: " + lastScore + "\n";
    }

}
