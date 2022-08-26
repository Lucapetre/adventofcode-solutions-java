package year2021.day08;

import utilities.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Display {

    private final List<String> shuffledNumbers;
    private final List<String> outputNumbers;
    private final Map<String,Integer> descramblerMap;
    private final Map<Integer,String> reverseMap;

    public Display(List<String> shuffledNumbers, List<String> outputNumbers) {
        this.shuffledNumbers = new ArrayList<>();
        this.outputNumbers = new ArrayList<>();
        this.descramblerMap = new HashMap<>();
        this.reverseMap = new HashMap<>();

        for (String string:shuffledNumbers) {
            this.shuffledNumbers.add(ArrayUtils.sortString(string));
        }
        for (String string:outputNumbers) {
            this.outputNumbers.add(ArrayUtils.sortString(string));
        }
    }

    private void addPair(String number,int value) {
        descramblerMap.put(number,value);
        reverseMap.put(value,number);
    }

    public void determineMaps() {
        for (String number:shuffledNumbers) {
            if(number.length() == 2) {
                addPair(number,1);
            } else if(number.length() == 3) {
                addPair(number,7);
            } else if(number.length() == 4) {
                addPair(number,4);
            } else if(number.length() == 7) {
                addPair(number,8);
            }
        }
        String one = reverseMap.get(1);
        char c1 = one.charAt(0);
        int cnt1 = 0;
        char c2 = one.charAt(1);
        char charMissingOnce;
        char charMissingTwice;

        for (String number:shuffledNumbers) {
            if(descramblerMap.containsKey(number)) {
                continue;
            }
            if (stringDoesNotContainChar(number, c1)) {
                cnt1++;
            }
        }
        if(cnt1 == 1) {
            charMissingOnce = c1;
            charMissingTwice = c2;
        } else {
            charMissingOnce = c2;
            charMissingTwice = c1;
        }

        for (String number:shuffledNumbers) {
            if(descramblerMap.containsKey(number)) {
                continue;
            }
            if (stringDoesNotContainChar(number, charMissingOnce)) {
                addPair(number,2);
            } else if (stringDoesNotContainChar(number, charMissingTwice)) {
                if(number.length() == 5) {
                    addPair(number,5);
                } else if(number.length() == 6) {
                    addPair(number, 6);
                }
            }
        }

        for (String number:shuffledNumbers) {
            if(descramblerMap.containsKey(number)) {
                continue;
            }
            if(number.length() == 5) {
                addPair(number,3);
            }
        }

        for (String number:shuffledNumbers) {
            if (descramblerMap.containsKey(number)) {
                continue;
            }
            if(number.length() == 6) {
                boolean containsAllSegmentsFromFour = true;
                String four = reverseMap.get(4);
                for (int i = 0; i < four.length(); i++) {
                    if(stringDoesNotContainChar(number, four.charAt(i))) {
                        containsAllSegmentsFromFour = false;
                        break;
                    }
                }
                if (containsAllSegmentsFromFour) {
                    addPair(number,9);
                } else addPair(number, 0);
            }
        }
    }

    public int getNumber() {
        int result = 0;
        for (String string:outputNumbers) {
            result = (result * 10) + descramblerMap.get(string);
        }
        return result;
    }

    private static boolean stringDoesNotContainChar(String str, char chr) {
        for(int i = 0; i < str.length(); i++)
            if(str.charAt(i) == chr)
                return false;
        return true;
    }


}
