package year2021.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolymerMapper {

    public static Map<String,Integer> polymerPairMap;
    public static Map<String,Integer> polymerSingleMap;
    public static Map<Integer,PolymerPair> integerPolymerPairMap;

    public static void instanceMaps(List<String> input) {

        polymerPairMap = new HashMap<>();
        polymerSingleMap = new HashMap<>();
        integerPolymerPairMap = new HashMap<>();

        for(String insertionRule:input) {
            PolymerPair polymerPair = new PolymerPair(insertionRule);
            polymerPairMap.putIfAbsent(polymerPair.getPairString(), polymerPairMap.size());
            polymerSingleMap.putIfAbsent(polymerPair.getInsertPolymer(), polymerSingleMap.size());
            integerPolymerPairMap.put(integerPolymerPairMap.size(),polymerPair);
        }
    }

}
