package year2021.day10;

import java.util.ArrayList;
import java.util.List;

public class ParenthesesStack {

    private final String line;
    private final List<Character> stack;
    private boolean corrupted; // true if corrupted, false if incomplete
    private int syntaxScore;
    private long autocompleteScore;

    public ParenthesesStack(String inputLine) {
        this.line = inputLine;

        this.stack = new ArrayList<>();
        this.syntaxScore = 0;
        this.autocompleteScore = 0;

        findIfCorruptedOrIncomplete();
    }

    private char getTopCharacter() {
        return stack.get(stack.size() - 1);
    }

    private void removeTopCharacter() {
        stack.remove(stack.size() - 1);
    }

    private static char getOppositeParenthesis(char parenthesis) {
        return switch (parenthesis) {
            case '(' -> ')';
            case ')' -> '(';
            case '[' -> ']';
            case ']' -> '[';
            case '{' -> '}';
            case '}' -> '{';
            case '<' -> '>';
            case '>' -> '<';
            default ->  throw new IllegalArgumentException("This function requires a parenthesis as an argument." +
                    " Argument was " + parenthesis);
        };
    }

    private static boolean isOpeningParenthesis(char parenthesis) {
        return parenthesis == '(' || parenthesis == '[' || parenthesis == '{' || parenthesis == '<';
    }

    private void assignScore(char parenthesis) {
        /*
        ): 3 points.
        ]: 57 points.
        }: 1197 points.
        >: 25137 points.
         */
        this.syntaxScore = switch (parenthesis) {
            case ')' -> 3;
            case ']' -> 57;
            case '}' -> 1197;
            case '>' -> 25137;
            default -> throw new IllegalArgumentException("Unexpected value: " + parenthesis);
        };
    }

    private boolean matchCharacter(char parenthesis) { // returns true if all is ok, false if the stack is corrupted

        if(isOpeningParenthesis(parenthesis)) { // add opening parenthesis to stack
            stack.add(parenthesis);
            return true;
        }
        // the stack is empty or parenthesis don't match
        if(stack.isEmpty() || getTopCharacter() != getOppositeParenthesis(parenthesis)) {
            assignScore(parenthesis);
            return false;
        }
        // the parenthesis match so we can remove the pair
        removeTopCharacter();
        return true;
    }

    private void autocomplete(char parenthesis) {
        autocompleteScore = (autocompleteScore * 5) + switch (parenthesis) {
            case '(' -> 1L;
            case '[' -> 2L;
            case '{' -> 3L;
            case '<' -> 4L;
            default -> throw new IllegalArgumentException("Unexpected value: " + parenthesis);
        };
    }

    private void findIfCorruptedOrIncomplete() {
        for (int i = 0; i < line.length(); i++) {
            boolean goOn = matchCharacter(line.charAt(i));
            if(!goOn) {
                corrupted = true;
                return;
            }
        }
        corrupted = false;
        while(!stack.isEmpty()) {
            autocomplete(getTopCharacter());
            removeTopCharacter();
        }
    }

    public int returnSyntaxScore() {
        if (corrupted) {
            return syntaxScore;
        }
        return 0;
    }

    public boolean isIncomplete() {
        return !corrupted;
    }

    public long returnAutocompleteScore() {
        return autocompleteScore;
    }
}
