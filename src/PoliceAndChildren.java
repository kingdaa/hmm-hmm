import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
  // 给一个n * n的二维数组，例如：
    // [[0, 1, 0]]
    // [[0, 2, 0]]
    // [[0, 1, 0]]

    // 2是一面墙，表示此路不通。
    // 0表示小孩儿。
    // 1表示警察叔叔。
    // 求小孩儿到离他最近的那个警察叔叔的距离。
    // 如果最终小孩儿碰不到警察叔叔，则距离为-1。
    // 注：墙到警察叔叔的距离无论如何都是-1，警察叔叔到自己的距离是0。

    // Running case 1:
    // [[0, 1, 0]]
    // [[0, 2, 0]]
    // [[0, 1, 0]]
    // Result:
    // [[1, 0, 1]]
    // [[2, -1, 2]]
    // [[1, 0, 1]]

    // Running case 2:
    // [[0, 2, 0]]
    // [[0, 2, 0]]
    // [[0, 2, 1]]
    // Result:
    // [[-1, -1, 2]]
    // [[-1, -1, 1]]
    // [[-1, -1, 0]]
    // Running case 3:
    // [[1, 0, 0]]
    // [[0, 0, 0]]
    // [[0, 0, 1]]
    // Result:
    // [[0, 1, 2]]
    // [[1, 2, 1]]
    // [[2, 1, 0]]
 */
public class PoliceAndChildren {
    public int[][] findShortestDistanceToPolice(int[][] graph) {
        int m = graph.length;
        if (m == 0) return null;
        int n = graph[0].length;
        if (n == 0) return null;
        int[][] res = new int[m][n];
        // Initialize the result array to -1
        for (int[] arr : res)
            Arrays.fill(arr, Integer.MAX_VALUE);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 1) {
                    boolean[][] visited = new boolean[m][n];
                    bfs(graph, i, j, visited, res);
                }
            }
        }
        return res;
    }

    private void bfs(int[][] graph, int i, int j, boolean[][] visited, int[][] res) {
        int m = graph.length;
        int n = graph[0].length;
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> stepsQueue = new LinkedList<>();
        queue.add(n * i + j);
        stepsQueue.add(0);
        while (!queue.isEmpty()) {
            int index = queue.remove();
            int step = stepsQueue.remove();
            int x = index / n, y = index % n;
//            System.out.println("x = " + x + " y = " + y);
            if (!visited[x][y]) {
                if (graph[x][y] == 1) res[x][y] = 0; // Police
                else {
                    res[x][y] = Math.min(res[x][y], step);
                }
            }
            if (x + 1 < m && !visited[x + 1][y] && graph[x + 1][y] != 2) {
                queue.add((x + 1) * n + y);
                stepsQueue.add(step + 1);
            }
            if (x - 1 >= 0 && !visited[x - 1][y] && graph[x - 1][y] != 2) {
                queue.add((x - 1) * n + y);
                stepsQueue.add(step + 1);
            }
            if (y + 1 < n && !visited[x][y + 1] && graph[x][y + 1] != 2) {
                queue.add(x * n + y + 1);
                stepsQueue.add(step + 1);
            }
            if (y - 1 >= 0 && !visited[x][y - 1] && graph[x][y - 1] != 2) {
                queue.add(x * n + y - 1);
                stepsQueue.add(step + 1);
            }
            visited[x][y] = true;
        }
    }

    public static void print2DArray(int[][] arr) {
        for (int[] a : arr) {
            for (int i : a) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] graph = {{0, 1, 0}, {0, 2, 0}, {0, 1, 0}};
        int[][] graph2 = {{0, 2, 0}, {0, 2, 0}, {0, 2, 1}};
        int[][] graph3 = {{1, 0, 0}, {0, 0, 0}, {0, 0, 1}};
        PoliceAndChildren sol = new PoliceAndChildren();
        print2DArray(graph);
        System.out.println();
        print2DArray(sol.findShortestDistanceToPolice(graph));
        System.out.println();
        print2DArray(sol.findShortestDistanceToPolice(graph2));
        System.out.println();
        print2DArray(sol.findShortestDistanceToPolice(graph3));
    }
}
