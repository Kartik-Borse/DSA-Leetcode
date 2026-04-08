class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int MOD = 1_000_000_007;

        // Process each query
        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];
            int k = q[2];
            int v = q[3];

            for (int i = l; i <= r; i += k) {
                nums[i] = (int)((1L * nums[i] * v) % MOD);
            }
        }

        // Compute XOR of final array
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        return xor;
    }
}