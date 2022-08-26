package year2021.day14;

import java.util.Arrays;

class PolymerCalculator {

    private final long[] numberOfPair;
    private final String initialPolymer;

    private int part;

    public PolymerCalculator(String initialPolymer) {
        this.initialPolymer = initialPolymer;
        this.numberOfPair = new long[PolymerMapper.polymerPairMap.size()];
        Arrays.fill(numberOfPair,0);
        for (int i = 0; i < initialPolymer.length() - 1; i++) {
            String pair = initialPolymer.substring(i,i+2);
            numberOfPair[PolymerMapper.polymerPairMap.get(pair)]++;
        }

        this.part = 0;
    }

    private void step() {
        long[] newPairs = new long[PolymerMapper.polymerPairMap.size()];
        Arrays.fill(newPairs,0);
        for (int i = 0; i < numberOfPair.length; i++) {
            long pairNumber = numberOfPair[i];
            PolymerPair pair = PolymerMapper.integerPolymerPairMap.get(i);
            newPairs[pair.getChild1ID()] += pairNumber;
            newPairs[pair.getChild2ID()] += pairNumber;
        }
        System.arraycopy(newPairs, 0, numberOfPair, 0, numberOfPair.length);
    }

    public void doNSteps(int numberOfSteps) {
        for (int i = 0; i < numberOfSteps; i++) {
            step();
        }
    }

    private long getMaxMinusMin() {
        long[] singlePolymer = new long[PolymerMapper.polymerSingleMap.size()];
        Arrays.fill(singlePolymer,0);
        //every character will be counted twice except the first and the last
        singlePolymer[PolymerMapper.polymerSingleMap.get
                (String.valueOf(initialPolymer.charAt(0)))]++;
        singlePolymer[PolymerMapper.polymerSingleMap.get
                (String.valueOf(initialPolymer.charAt(initialPolymer.length() - 1)))]++;
        for (int i = 0; i < numberOfPair.length; i++) {
            String pair = PolymerMapper.integerPolymerPairMap.get(i).getPairString();
            String leftChar = String.valueOf(pair.charAt(0));
            String rightChar = String.valueOf(pair.charAt(1));
            singlePolymer[PolymerMapper.polymerSingleMap.get(leftChar)] += numberOfPair[i];
            singlePolymer[PolymerMapper.polymerSingleMap.get(rightChar)] += numberOfPair[i];
        }
        long min = (long) 2e18;
        long max = 0;
        for (int i = 0; i < singlePolymer.length; i++) {
            singlePolymer[i] /= 2;
            min = Math.min(min,singlePolymer[i]);
            max = Math.max(max,singlePolymer[i]);
        }
        return max - min;
    }


    @Override
    public String toString() {
        return "Part " + (++part) + ":\n" +
                "Max - min: " + getMaxMinusMin();
    }
}
