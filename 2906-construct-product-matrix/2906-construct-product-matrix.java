class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int size = n * m;
        int MOD = 12345;

        int[] flat = new int[size];

        // Step 1: Flatten matrix
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                flat[index++] = grid[i][j] % MOD;
            }
        }

        int[] result = new int[size];

        // Step 2: Prefix pass
        int prefix = 1;
        for (int i = 0; i < size; i++) {
            result[i] = prefix;
            prefix = (prefix * flat[i]) % MOD;
        }

        // Step 3: Suffix pass
        int suffix = 1;
        for (int i = size - 1; i >= 0; i--) {
            result[i] = (result[i] * suffix) % MOD;
            suffix = (suffix * flat[i]) % MOD;
        }

        // Step 4: Convert back to 2D
        int[][] ans = new int[n][m];
        index = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans[i][j] = result[index++];
            }
        }

        return ans;
    }
}