import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        // Map to store lists of indices for each unique number
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            indexMap.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        int minDistance = Integer.MAX_VALUE;
        boolean found = false;
        
        for (List<Integer> indices : indexMap.values()) {
            // We need at least 3 occurrences to form a good tuple
            if (indices.size() >= 3) {
                found = true;
                // Check every contiguous triplet of indices for this number
                for (int m = 0; m <= indices.size() - 3; m++) {
                    int i = indices.get(m);
                    int k = indices.get(m + 2);
                    
                    // Distance is 2 * (max_index - min_index)
                    int currentDistance = 2 * (k - i);
                    minDistance = Math.min(minDistance, currentDistance);
                }
            }
        }
        
        return found ? minDistance : -1;
    }
}