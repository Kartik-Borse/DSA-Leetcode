import java.util.*;

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long total = 0;
        for (int[] row : grid) {
            for (int v : row) total += v;
        }

        Map<Integer, Integer> topFreq = new HashMap<>();
        Map<Integer, Integer> botFreq = new HashMap<>();
        for (int[] row : grid) {
            for (int v : row) botFreq.put(v, botFreq.getOrDefault(v, 0) + 1);
        }

        long topSum = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                int v = grid[i][j];
                topSum += v;
                topFreq.put(v, topFreq.getOrDefault(v, 0) + 1);
                botFreq.put(v, botFreq.get(v) - 1);
                if (botFreq.get(v) == 0) botFreq.remove(v);
            }
            if (check(topSum, total - topSum, topFreq, i + 1, n, grid, true, i, -1)) return true;
            if (check(total - topSum, topSum, botFreq, m - 1 - i, n, grid, false, i, -1)) return true;
        }

        Map<Integer, Integer> leftFreq = new HashMap<>();
        Map<Integer, Integer> rightFreq = new HashMap<>();
        for (int[] row : grid) {
            for (int v : row) rightFreq.put(v, rightFreq.getOrDefault(v, 0) + 1);
        }

        long leftSum = 0;
        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++) {
                int v = grid[i][j];
                leftSum += v;
                leftFreq.put(v, leftFreq.getOrDefault(v, 0) + 1);
                rightFreq.put(v, rightFreq.get(v) - 1);
                if (rightFreq.get(v) == 0) rightFreq.remove(v);
            }
            if (check(leftSum, total - leftSum, leftFreq, m, j + 1, grid, true, -1, j)) return true;
            if (check(total - leftSum, leftSum, rightFreq, m, n - 1 - j, grid, false, -1, j)) return true;
        }

        return false;
    }

    private boolean check(long s1, long s2, Map<Integer, Integer> freq, int r, int c, int[][] grid, boolean first, int ri, int ci) {
        if (s1 == s2) return true;
        long d = s1 - s2;
        if (d <= 0 || d > Integer.MAX_VALUE || !freq.containsKey((int) d)) return false;
        if (r > 1 && c > 1) return true;

        int target = (int) d;
        if (r == 1) {
            int row = (ri != -1) ? (first ? ri : ri + 1) : 0;
            int cs = (ci != -1 && !first) ? ci + 1 : 0;
            int ce = (ci != -1 && first) ? ci : grid[0].length - 1;
            return grid[row][cs] == target || grid[row][ce] == target;
        } else {
            int col = (ci != -1) ? (first ? ci : ci + 1) : 0;
            int rs = (ri != -1 && !first) ? ri + 1 : 0;
            int re = (ri != -1 && first) ? ri : grid.length - 1;
            return grid[rs][col] == target || grid[re][col] == target;
        }
    }
}