package year2021.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaveMapper {

    private final Map<String,Integer> caveMap;
    private final Map<Integer,String> reverseCaveMap;
    private final boolean[] bigCaves;
    private int size;

    private int part;

    private int totalPaths;

    private final List<List<Integer>> adjacencyLists;

    private void addPair(String cave,int value) {
        caveMap.put(cave,value);
        reverseCaveMap.put(value,cave);
    }

    public CaveMapper(List<String> input) {
        caveMap = new HashMap<>();
        reverseCaveMap = new HashMap<>();
        addPair("start",0);
        this.size = 1;
        this.part = 1;

        for(String line:input) {
            String[] parts = line.split("-");
            for (int i = 0; i < 2; i++) {
                if (!caveMap.containsKey(parts[i]) && !parts[i].equals("end")) {
                    addPair(parts[i], size++);
                }
            }
        }

        addPair("end",size++);
        
        bigCaves = new boolean[size];
        adjacencyLists = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            String cave = reverseCaveMap.get(i);
            bigCaves[i] = cave.equals(cave.toUpperCase());
            
            adjacencyLists.add(new ArrayList<>());
        }

        for(String line:input) {
            String[] parts = line.split("-");
            int first = caveMap.get(parts[0]);
            int second = caveMap.get(parts[1]);

            if(second != 0) { //do not add a path back to start
                adjacencyLists.get(first).add(second);
            }
            if(first != 0) { //do not add a path back to start
                adjacencyLists.get(second).add(first);
            }
        }

    }

    public void findTotalPaths() {
        BacktrackPaths backtrackPaths = new BacktrackPaths(bigCaves,size,adjacencyLists,part);
        this.totalPaths = backtrackPaths.getTotalPaths();
    }

    @Override
    public String toString() {
        return "Part " + (part++) + ":\n" +
                "Total paths: " + totalPaths;
    }
}
