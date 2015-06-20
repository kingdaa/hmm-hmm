import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
// 给定二维数组，表示一片海洋，1代表有岛，0代表没岛。返回相连岛屿的大小。
// Input:
//[["1", "0", "0", "1"],
// ["1", "0", "0", "1"],
// ["1", "1", "0", "0"]]
// Output:
// [4, 2]

// Input:
//[["1", "1", "1", "0"],
// ["1", "0", "1", "0"],
// ["1", "1", "1", "0"]]
// Output:
// [8]
 */
public class IslandSizes {
    public List<Integer> findAllIslands(int[][] ocean) {
        List<Integer> res = new ArrayList<>();
        int m = ocean.length;
        if (m < 1) return res;
        int n = ocean[0].length;
        if (n < 1) return res;
        // DFS to mark different islands
        int mark = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (ocean[i][j] == 1) {
                    dfs(ocean, i, j, mark);
                    mark--;
                }
            }
        }
        // Count sizes of every island
        Map<Integer, Integer> islands = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (ocean[i][j] < 0) {
                    if (!islands.containsKey(ocean[i][j])) islands.put(ocean[i][j], 1);
                    else islands.put(ocean[i][j], islands.get(ocean[i][j]) + 1);
                    // Optional step: recover the original input
                    ocean[i][j] = 1;
                }
            }
        }
        for (int k : islands.keySet()) {
            res.add(islands.get(k));
        }
        return res;
    }

    private void dfs(int[][] ocean, int i, int j, int mark) {
        if (i < 0 || i > ocean.length - 1 || j < 0 || j > ocean[0].length - 1 || ocean[i][j] != 1) return;
        ocean[i][j] = mark;
        dfs(ocean, i + 1, j, mark);
        dfs(ocean, i - 1, j, mark);
        dfs(ocean, i, j + 1, mark);
        dfs(ocean, i, j - 1, mark);
    }

    public static void main(String[] args) {
        int[][] ocean = {{1, 0, 0, 1}, {1, 0, 0, 1}, {1, 1, 0, 0}};
        int[][] ocean2 = {{1, 1, 1, 0}, {1, 0, 1, 0}, {1, 1, 1, 0}};
        IslandSizes sol = new IslandSizes();
        System.out.println(sol.findAllIslands(ocean));
        System.out.println(sol.findAllIslands(ocean2));
    }
}
