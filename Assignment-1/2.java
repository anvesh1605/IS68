package DSA_Assignment_01;

import java.util.*;

public class ques2 {

     public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        int n = nums.length;
        int[] res = new int[n-k+1];
        int idx = 0;

        for (int i=0; i<n; i++) {

            while (!dq.isEmpty() && dq.peekFirst() <= i-k) 
                dq.pollFirst();

            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) 
                dq.pollLast();

            dq.offerLast(i);

            if (i >= k-1) res[idx++] = nums[dq.peekFirst()];
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(maxSlidingWindow(nums, 3)));
    }
}