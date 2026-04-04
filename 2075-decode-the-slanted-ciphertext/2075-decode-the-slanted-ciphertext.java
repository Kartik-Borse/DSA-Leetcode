class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int n = encodedText.length();
        if (n == 0) return "";
        
        int cols = n / rows;
        StringBuilder sb = new StringBuilder();
        
        for (int c = 0; c < cols; c++) {
            for (int r = 0, currCol = c; r < rows && currCol < cols; r++, currCol++) {
                int index = r * cols + currCol;
                if (index < n) {
                    sb.append(encodedText.charAt(index));
                }
            }
        }
        
        int i = sb.length() - 1;
        while (i >= 0 && sb.charAt(i) == ' ') {
            i--;
        }
        
        return sb.substring(0, i + 1);
    }
}