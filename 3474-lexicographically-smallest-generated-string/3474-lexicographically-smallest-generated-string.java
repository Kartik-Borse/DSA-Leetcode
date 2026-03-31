class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int L = n + m - 1;
        
        char[] res = new char[L];
        boolean[] fixed = new boolean[L];
        
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (fixed[i + j] && res[i + j] != str2.charAt(j)) {
                        return "";
                    }
                    res[i + j] = str2.charAt(j);
                    fixed[i + j] = true;
                }
            }
        }
        
        for (int i = 0; i < L; i++) {
            if (!fixed[i]) {
                res[i] = 'a';
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                if (isMatch(res, i, str2)) {

                    boolean fixed_flag = false;
                    for (int j = m - 1; j >= 0; j--) {
                        if (!fixed[i + j]) {
                            res[i + j] = 'b';

                            fixed_flag = true;
                            break;
                        }
                    }
                    if (!fixed_flag) return "";
                }
            }
        }
        for (int i = 0; i < n; i++) {
            boolean match = isMatch(res, i, str2);
            if (str1.charAt(i) == 'T' && !match) return "";
            if (str1.charAt(i) == 'F' && match) return "";
        }
        
        return new String(res);
    }
    
    private boolean isMatch(char[] res, int start, String str2) {
        for (int j = 0; j < str2.length(); j++) {
            if (res[start + j] != str2.charAt(j)) return false;
        }
        return true;
    }
}