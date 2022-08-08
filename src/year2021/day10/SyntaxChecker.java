package year2021.day10;

import java.util.ArrayList;
import java.util.List;

public class SyntaxChecker {

    private int syntaxScore;
    private final long autocompleteScore;

    public SyntaxChecker(List<String> input) {
        this.syntaxScore = 0;
        List<Long> autocompleteScores = new ArrayList<>();

        for(String line: input) {
            ParenthesesStack parenthesesStack = new ParenthesesStack(line);
            syntaxScore += parenthesesStack.returnSyntaxScore();
            if(parenthesesStack.isIncomplete()) {
                autocompleteScores.add(parenthesesStack.returnAutocompleteScore());
            }
        }
        this.autocompleteScore = autocompleteScores
                .stream().sorted().toList().get(autocompleteScores.size()/2);
    }

    @Override
    public String toString() {
        return "Part1:\n" +
                "Syntax score: " + syntaxScore + "\n" +
                "Part2:\n" +
                "Autocomplete score: " + autocompleteScore;

    }

}
