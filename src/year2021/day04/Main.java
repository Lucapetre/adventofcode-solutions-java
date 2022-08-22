package year2021.day04;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) {

        Scanner inputReader;
        try {
            inputReader = new Scanner(Paths.get("input.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Integer> numbersToCall = new ArrayList<>();
        List<BingoBoard> bingoBoards = new ArrayList<>();

        String numberLine = inputReader.nextLine();
        String[] numberStrings = numberLine.split(",");

        for (String number: numberStrings) {
            numbersToCall.add(Integer.valueOf(number));
        }

        while(inputReader.hasNextInt()) {
            ArrayList<Integer> bingoBoardNumbers = new ArrayList<>();
            for (int i = 0; i < 25; i++) {
                bingoBoardNumbers.add(inputReader.nextInt());
            }
            bingoBoards.add(new BingoBoard(bingoBoardNumbers));
        }

        BingoCounter bingoCounter = new BingoCounter(numbersToCall, bingoBoards);
        bingoCounter.callAllNumbers();
        System.out.println(bingoCounter);
    }

}
