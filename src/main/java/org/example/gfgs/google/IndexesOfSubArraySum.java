package org.example.gfgs.google;

public class IndexesOfSubArraySum {

    public static void main(String[] args){
        int[] arr = {5,3,4};
        int target=2;

        int[] ans = indexesOfSubArraySumSlidingWindowApproach(arr,target);
        for(int i=0;i<ans.length;i++){
            System.out.print(ans[i]+",");
        }
    }

    // time complexity : (O)n^2 and space complexity : (0)n
    private static int[] indexesOfSubArraySum(int[] arr, int target) {
        for(int i=0;i<arr.length;i++){
            int sum=0;
            for(int j=i;j<arr.length;j++){
                sum +=arr[j];
                if(sum==target){
                    return new int[]{i+1,j+1};
                }else if(sum>target) break;
            }
        }
        return new int[]{-1};
    }

    // sliding window approach
    private static int[] indexesOfSubArraySumSlidingWindowApproach(int[] arr, int target){
        int n = arr.length;
        int start=0;
        int sum=0;


        for(int i=0;i<n;i++){

            sum += arr[i];

            while(sum>target && start<=i){
                sum = sum - arr[start];
                start++;
            }
            if(sum == target){
                return new int[]{i+1,start+1};
            }
        }
        return new int[]{-1};
    }
}
