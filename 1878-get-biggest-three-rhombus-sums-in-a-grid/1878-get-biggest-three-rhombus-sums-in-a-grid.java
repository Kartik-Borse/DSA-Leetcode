import java.util.*;

class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // radius 0
                set.add(grid[i][j]);

                int maxRadius = Math.min(Math.min(i, j), Math.min(m - 1 - i, n - 1 - j));

                for (int k = 1; k <= maxRadius; k++) {

                    int sum = 0;

                    int x = i - k;
                    int y = j;

                    // top -> right
                    for (int d = 0; d < k; d++)
                        sum += grid[x + d][y + d];

                    // right -> bottom
                    for (int d = 0; d < k; d++)
                        sum += grid[x + k + d][y + k - d];

                    // bottom -> left
                    for (int d = 0; d < k; d++)
                        sum += grid[x + 2 * k - d][y - d];

                    // left -> top
                    for (int d = 0; d < k; d++)
                        sum += grid[x + k - d][y - k + d];

                    set.add(sum);
                }
            }
        }

        int size = Math.min(3, set.size());
        int[] ans = new int[size];

        int idx = 0;
        for (int val : set) {
            if (idx == size) break;
            ans[idx++] = val;
        }

        return ans;
    }
}