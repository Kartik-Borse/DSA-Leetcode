import java.util.*;

class Solution {
    public boolean canBeEqual(String s1, String s2) {
        char[] s1_even = {s1.charAt(0), s1.charAt(2)};
        char[] s1_odd  = {s1.charAt(1), s1.charAt(3)};
        
        char[] s2_even = {s2.charAt(0), s2.charAt(2)};
        char[] s2_odd  = {s2.charAt(1), s2.charAt(3)};
        
        Arrays.sort(s1_even);
        Arrays.sort(s1_odd);
        Arrays.sort(s2_even);
        Arrays.sort(s2_odd);
        
        return Arrays.equals(s1_even, s2_even) &&
               Arrays.equals(s1_odd, s2_odd);
    }
}