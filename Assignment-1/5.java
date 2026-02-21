package DSA_Assignment_01;

import java.util.Arrays;

public class ques5 {
    public static void main(String[] args) {
        int[][] costMatrix = {
            {10, 5, 3},
            {1, 2, 3},
            {2, 10, 1}
        }; 
        System.out.println("Cost Matrix:");
        for (int i=0; i<costMatrix.length; i++) {
            System.out.println("Person " + i + ": " + Arrays.toString(costMatrix[i]));
        }

        int minCost = minimizeCost(costMatrix);
        System.out.println("\nMinimum Cost to assign all jobs: " + minCost);
    }

     public static int minimizeCost(int[][] cost) {
        int n = cost.length;
        int maxMask = 1 << n; // 0 to 2^n-1

        int[] dp = new int[maxMask];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);

        dp[0] = 0;
        
        for (int mask = 0; mask < maxMask; mask++){ // 0 to 2^n-1
            int i = Integer.bitCount(mask); // no of people assigned jobs so far 
            if (i >= n) continue; // if all people assigned jobs alr
            for (int j=0; j<n; j++){ // try assigning j'th free job
                if ((mask & (1 << j)) == 0) { // if j'th job is free (bit not set)
                    int newMask = mask | (1 << j);
                    dp[newMask] = Math.min(dp[newMask], dp[mask] + cost[i][j]);
                }
            }
        }

        return dp[maxMask - 1];
     }
    
}
