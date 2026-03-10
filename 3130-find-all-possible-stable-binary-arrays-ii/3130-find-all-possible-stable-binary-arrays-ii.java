class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        int MOD = 1_000_000_007;
        // dp[i][j][0] -> count ending with 0 using i zeros and j ones
        // dp[i][j][1] -> count ending with 1 using i zeros and j ones
        long[][][] dp = new long[zero + 1][one + 1][2];

        // Base Case: Fill sequences consisting only of 0s or only of 1s
        // up to the limit, as they are stable by definition.
        for (int i = 0; i <= Math.min(zero, limit); i++) dp[i][0][0] = 1;
        for (int j = 0; j <= Math.min(one, limit); j++) dp[0][j][1] = 1;

        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                // To end in 0:
                // We can add a 0 to any stable array ending in 0 or 1.
                // dp[i][j][0] = dp[i-1][j][0] + dp[i-1][j][1]
                dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                if (i > limit) {
                    // Subtract cases where adding a 0 creates a sequence of (limit + 1) zeros.
                    // This happens if the state at (i - limit - 1, j) ended in a 1, 
                    // and we then appended (limit + 1) zeros.
                    dp[i][j][0] = (dp[i][j][0] - dp[i - limit - 1][j][1] + MOD) % MOD;
                }

                // To end in 1:
                // Same logic as above, just mirrored for the 'one' count.
                dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                if (j > limit) {
                    dp[i][j][1] = (dp[i][j][1] - dp[i][j - limit - 1][0] + MOD) % MOD;
                }
            }
        }

        return (int) ((dp[zero][one][0] + dp[zero][one][1]) % MOD);
    }
}