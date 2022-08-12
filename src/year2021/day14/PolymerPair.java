package year2021.day14;

public class PolymerPair {

    private final String pairString;
    private final String child1String;
    private final String child2String;

    public PolymerPair(String pairInsertion) {
        String[] parts = pairInsertion.split(" -> ");
        this.pairString = parts[0];
        this.child1String = parts[0].charAt(0) + parts[1];
        this.child2String = parts[1] + parts[0].charAt(1);
    }

    public String getPairString() {
        return pairString;
    }


    public int getChild1ID() {
        return PolymerMapper.polymerPairMap.get(child1String);
    }

    public int getChild2ID() {
        return PolymerMapper.polymerPairMap.get(child2String);
    }

    public String getInsertPolymer() {
        return String.valueOf(child2String.charAt(0));
    }
}
