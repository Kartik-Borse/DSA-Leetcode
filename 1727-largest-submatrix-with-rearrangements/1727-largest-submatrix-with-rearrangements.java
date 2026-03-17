import java.util.*;

class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[] height = new int[n];
        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    height[j] = 0;
                } else {
                    height[j] += 1;
                }
            }

            int[] sorted = height.clone();
            Arrays.sort(sorted);

            for (int k = n - 1; k >= 0; k--) {
                int h = sorted[k];
                int width = n - k;
                maxArea = Math.max(maxArea, h * width);
            }
        }

        return maxArea;
    }
}