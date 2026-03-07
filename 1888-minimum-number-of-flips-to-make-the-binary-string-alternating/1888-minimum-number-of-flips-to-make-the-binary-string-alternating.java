class Solution {
    public int minFlips(String s) {
        int n = s.length();
        s = s + s;

        int diff1 = 0, diff2 = 0;
        int res = Integer.MAX_VALUE;

        for(int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if(i % 2 == 0){
                if(c != '0') diff1++;
                if(c != '1') diff2++;
            } else {
                if(c != '1') diff1++;
                if(c != '0') diff2++;
            }

            if(i >= n){
                char left = s.charAt(i - n);

                if((i - n) % 2 == 0){
                    if(left != '0') diff1--;
                    if(left != '1') diff2--;
                } else {
                    if(left != '1') diff1--;
                    if(left != '0') diff2--;
                }
            }

            if(i >= n - 1){
                res = Math.min(res, Math.min(diff1, diff2));
            }
        }

        return res;
    }
}