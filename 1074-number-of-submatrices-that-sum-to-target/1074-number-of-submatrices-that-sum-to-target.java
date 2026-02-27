class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int count = 0;

        for (int r1 = 0; r1 < n; r1++) {
            int[] colSum = new int[m];

            for (int r2 = r1; r2 < n; r2++) {

                for (int c = 0; c < m; c++) {
                    colSum[c] += matrix[r2][c];
                }
                count += subarraySum(colSum, target);
            }
        }
        return count;
    }

    private int subarraySum(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0, res = 0;
        for (int num : arr) {
            sum += num;
            res += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}