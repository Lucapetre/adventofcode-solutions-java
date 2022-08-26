package year2021.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BacktrackPaths {

    private final boolean[] bigCaves;
    private final int size;
    private final List<List<Integer>> adjacencyLists;

    private int paths;
    private final int[] visited;
    private final List<Integer> backtrackingStack;

    private final int part;

    private int over1;

    public BacktrackPaths(boolean[] bigCaves, int size, List<List<Integer>> adjacencyLists,int part) {
        this.bigCaves = bigCaves;
        this.size = size;
        this.adjacencyLists = adjacencyLists;
        this.part = part;
        this.over1 = 0;

        this.paths = 0;
        this.visited = new int[size];
        Arrays.fill(visited,0);
        this.backtrackingStack = new ArrayList<>(size);
        backtrackingStack.add(0);
        visited[0] = 1;

        backtrack();
    }

    private boolean stackIsValid() {
        int top = backtrackingStack.get(backtrackingStack.size() - 1);
        if(part == 1){
            return visited[top] <= 1 || bigCaves[top]; // the current cave was visited (at most) once or is big
        }
        if(visited[top] <= 1 || bigCaves[top]) {
            return true;
        }
        return visited[top] == 2 && over1 == 1;
    }

    private boolean isSolution() {
        int top = backtrackingStack.get(backtrackingStack.size() - 1);
        return top == size - 1; // current cave is end
    }

    private void backtrack() {

        int level = backtrackingStack.size();
        List<Integer> nextNodes = adjacencyLists.get(backtrackingStack.get(level - 1));
        for (Integer adjacentNode: nextNodes) {
            backtrackingStack.add(adjacentNode);
            visited[adjacentNode]++;
            if(visited[adjacentNode] == 2 && !bigCaves[adjacentNode]) {
                over1++;
            }

            if(stackIsValid()) {
                if (isSolution()) {
                    paths++;
                }
                else backtrack();
            }

            backtrackingStack.remove(backtrackingStack.size() - 1);
            if(visited[adjacentNode] == 2 && !bigCaves[adjacentNode]) {
                over1--;
            }
            visited[adjacentNode]--;
        }
    }

    public int getTotalPaths() {
        return this.paths;
    }
}
