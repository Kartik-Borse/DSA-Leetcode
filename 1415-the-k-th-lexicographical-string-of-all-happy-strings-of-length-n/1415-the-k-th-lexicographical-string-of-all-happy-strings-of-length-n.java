class Solution {
    int count = 0;
    String ans = "";

    public String getHappyString(int n, int k) {
        backtrack(n, k, new StringBuilder());
        return ans;
    }

    private void backtrack(int n, int k, StringBuilder sb) {
        if (sb.length() == n) {
            count++;
            if (count == k) {
                ans = sb.toString();
            }
            return;
        }

        for (char c : new char[]{'a','b','c'}) {
            if (sb.length() > 0 && sb.charAt(sb.length()-1) == c) {
                continue;
            }

            sb.append(c);
            backtrack(n, k, sb);
            sb.deleteCharAt(sb.length()-1);

            if (!ans.equals("")) return; 
        }
    }
}