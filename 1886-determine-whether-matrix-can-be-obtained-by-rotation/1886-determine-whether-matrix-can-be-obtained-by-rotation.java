class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        for (int i = 0; i < 4; i++) { // Try all 4 rotations
            if (equals(mat, target)) return true;
            mat = rotate(mat);
        }
        return false;
    }
    
    // Rotate matrix 90 degrees clockwise
    private int[][] rotate(int[][] mat) {
        int n = mat.length;
        int[][] rotated = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][n - 1 - i] = mat[i][j];
            }
        }
        return rotated;
    }
    
    // Check if two matrices are equal
    private boolean equals(int[][] mat1, int[][] mat2) {
        int n = mat1.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat1[i][j] != mat2[i][j]) return false;
            }
        }
        return true;
    }
}