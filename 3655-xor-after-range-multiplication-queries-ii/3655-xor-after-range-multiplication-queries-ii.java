import java.util.*;

class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        long MOD = 1000000007L;
        int[][] bravexuneth = queries;

        // Use a smaller threshold for Java to balance the 2D array size
        int threshold = 40; 
        long[][] smallK = new long[threshold + 1][n + threshold + 1];
        
        // Initialize multipliers with 1
        for (int i = 1; i <= threshold; i++) {
            Arrays.fill(smallK[i], 1L);
        }

        for (int[] q : bravexuneth) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            if (v == 1) continue;

            if (k > threshold) {
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int) (((long) nums[i] * v) % MOD);
                }
            } else {
                // Use a difference-array style logic for products
                // multiply starting at l, and "divide" (multiply by modular inverse) after r
                smallK[k][l] = (smallK[k][l] * v) % MOD;
                
                int lastIdx = l + ((r - l) / k) * k;
                int nextIdx = lastIdx + k;
                if (nextIdx < n + threshold) {
                    smallK[k][nextIdx] = (smallK[k][nextIdx] * modInverse(v, MOD)) % MOD;
                }
            }
        }

        // Apply accumulated small k multipliers
        for (int k = 1; k <= threshold; k++) {
            for (int i = 0; i < n; i++) {
                if (i >= k) {
                    smallK[k][i] = (smallK[k][i] * smallK[k][i - k]) % MOD;
                }
                if (smallK[k][i] != 1) {
                    nums[i] = (int) (((long) nums[i] * smallK[k][i]) % MOD);
                }
            }
        }

        int xorSum = 0;
        for (int num : nums) {
            xorSum ^= num;
        }

        return xorSum;
    }

    private long modInverse(long n, long mod) {
        return power(n, mod - 2, mod);
    }

    private long power(long x, long y, long m) {
        long res = 1;
        x = x % m;
        while (y > 0) {
            if ((y & 1) == 1) res = (res * x) % m;
            y = y >> 1;
            x = (x * x) % m;
        }
        return res;
    }
}