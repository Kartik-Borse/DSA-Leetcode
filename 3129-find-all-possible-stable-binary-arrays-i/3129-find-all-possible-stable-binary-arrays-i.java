class Solution {
    private static final int MOD = 1_000_000_007;
    private Integer[][][][] memo;

    public int numberOfStableArrays(int zero, int one, int limit) {
        memo = new Integer[zero + 1][one + 1][2][limit + 1];
        int res = 0;
        if (zero > 0) res = (res + dfs(zero - 1, one, 0, 1, limit)) % MOD;
        if (one > 0) res = (res + dfs(zero, one - 1, 1, 1, limit)) % MOD;
        return res;
    }

    private int dfs(int z, int o, int last, int count, int limit) {
        if (z == 0 && o == 0) return 1;
        if (memo[z][o][last][count] != null) return memo[z][o][last][count];

        int res = 0;
        if (last == 0) {
            if (z > 0 && count < limit) res = (res + dfs(z - 1, o, 0, count + 1, limit)) % MOD;
            if (o > 0) res = (res + dfs(z, o - 1, 1, 1, limit)) % MOD;
        } else {
            if (o > 0 && count < limit) res = (res + dfs(z, o - 1, 1, count + 1, limit)) % MOD;
            if (z > 0) res = (res + dfs(z - 1, o, 0, 1, limit)) % MOD;
        }

        return memo[z][o][last][count] = res;
    }
}