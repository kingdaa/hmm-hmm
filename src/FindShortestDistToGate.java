import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by King on 4/8/15.
 * w是wall，然后g是gates，从“_”开始，找出所有到gates的距离
 * _ W G _
 * _ _ _ W
 * _ W _ W
 * G W _ _
 */
class Point {
    int x;
    int y;
    int currDist;

    Point(int x, int y, int currDist) {
        this.x = x;
        this.y = y;
        this.currDist = currDist;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCurrDist() {
        return currDist;
    }

    public void setCurrDist(int currDist) {
        this.currDist = currDist;
    }
}

public class FindShortestDistToGate {
    public String[] findDistToGates(char[][] maze) {
        int i, j;
        int n = maze.length;
        int m = maze[0].length;
        String[] res = new String[n];
        String[][] resArr = new String[n][m];
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                resArr[i][j] = String.valueOf(maze[i][j]);
            }
        }

        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                if ("_".equals(resArr[i][j])) {
                    boolean[][] visited = new boolean[n][m];
                    bfs(resArr, visited, i, j, n, m);
                }
            }
        }
        for (i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (j = 0; j < m; j++) {
                sb.append(resArr[i][j]);
                sb.append(' ');
            }
            sb.setLength(sb.length() - 1);
            res[i] = sb.toString();
        }
        return res;
    }

    private void bfs(String[][] resArr, boolean[][] visited, int i, int j, int n, int m) {
        Queue<Point> queue = new LinkedList<Point>();
        queue.add(new Point(i, j, 0));
        while (!queue.isEmpty()) {
            Point point = queue.remove();
            int x = point.getX();
            int y = point.getY();
            int curDist = point.getCurrDist();
//            System.out.println(x);
//            System.out.println(y);
            if ("G".equals(resArr[x][y])) {
                resArr[i][j] = String.valueOf(curDist);
                return;
            }
            visited[x][y] = true;
            if (x + 1 < n && !visited[x + 1][y] && !"W".equals(resArr[x + 1][y])) {
                queue.add(new Point(x + 1, y, curDist + 1));
            }
            if (x - 1 >= 0 && !visited[x - 1][y] && !"W".equals(resArr[x - 1][y])) {
                queue.add(new Point(x - 1, y, curDist + 1));
            }
            if (y + 1 < m && !visited[x][y + 1] && !"W".equals(resArr[x][y + 1])) {
                queue.add(new Point(x, y + 1, curDist + 1));
            }
            if (y - 1 >= 0 && !visited[x][y - 1] && !"W".equals(resArr[x][y - 1])) {
                queue.add(new Point(x, y - 1, curDist + 1));
            }
        }
    }

    public static void main(String[] args) {
        char[][] test = {{'_', 'W', 'G', '_'}, {'_', '_', '_', 'W'}, {'_', 'W', 'W', 'W'}, {'G', 'W', '_', '_'}};
        FindShortestDistToGate sol = new FindShortestDistToGate();
        String[] res = sol.findDistToGates(test);
        for (String str : res) {
            System.out.println(str);
        }
    }
}
