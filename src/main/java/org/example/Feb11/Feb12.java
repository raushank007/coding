package org.example.Feb11;

import java.util.*;

public class Feb12 {
    public static void main(String[] args){
        int[] arr = {-1,0,1,2,-1,-4};

        //1. 3 Sum -Brute Force
        List<List<Integer>> bruteForce3Sum = threeSumBruteForce(arr);
        System.out.println("bruteForceThreeSum: "+ bruteForce3Sum);

        //1 . 3 Sum - Two pointer
        List<List<Integer>> OptimizedThreeSum = threeSumOptimized(arr);
        System.out.println("OptimizedThreeSum: "+ OptimizedThreeSum);
    }

    private static List<List<Integer>> threeSumOptimized(int[] arr) {
        Arrays.sort(arr);
        int n=arr.length;
        Set<List<Integer>> ans = new HashSet<>();
        for(int i=0;i<n;i++){
            int start=i+1;
            int end=n-1;
            while(start<end){
                int sum = arr[i] + arr[start] + arr[end];
                if(sum==0){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(arr[i]);temp.add(arr[start]);temp.add(arr[end]);
                    start++;end--;
                    ans.add(temp);
                }else if(sum>0){
                    end--;
                }else{
                    start++;
                }
            }
        }
        return new ArrayList<>(ans);
    }

    private static List<List<Integer>> threeSumBruteForce(int[] arr) {
        int n = arr.length;
        HashSet<List<Integer>> set = new HashSet<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    if(i!=j && i!=k && k!=j && (arr[i]+arr[j]+arr[k]==0)){
                        List<Integer> temp = new ArrayList<>();
                        temp.add(arr[i]);temp.add(arr[j]);temp.add(arr[k]);
                        Collections.sort(temp);
                        set.add(temp);
                    }
                }
            }
        }
        return new  ArrayList<>(set);
    }
}
