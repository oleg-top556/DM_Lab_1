import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] graph = {{0, 0, 7, 0, 0, 0, 46, 98},
                {0, 0, 33, 0, 0, 99, 0, 0},
                {7, 33, 0, 99, 92, 28, 0, 64},
                {0, 0, 99, 0, 15, 52, 0, 0},
                {0, 0, 92, 15, 0, 0, 0, 58},
                {0, 99, 28, 52, 0, 0, 0, 0},
                {46, 0, 0, 0, 0, 0, 0, 36},
                {98, 0, 64, 0, 58, 0, 36, 0}};

        int[] parent = new int[graph.length];
        int[] key = new int[graph.length];
        boolean[] mstSet = new boolean[graph.length];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(mstSet, false);

        key[0] = 0;
        parent[0] = -1;

        for (int i = 0; i < graph.length - 1; i++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;

            for (int v = 0; v < graph.length; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent, graph);
    }

    public static int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < key.length; i++) {
            if (!mstSet[i] && key[i] < min) {
                min = key[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    public static void printMST(int[] parent, int[][] graph) {
        System.out.println("Ребро \tВага");
        int totalWeight = 0;
        for (int i = 1; i < graph.length; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
            totalWeight += graph[i][parent[i]];
        }
        System.out.println("Сумарна вага: " + totalWeight);
    }
}