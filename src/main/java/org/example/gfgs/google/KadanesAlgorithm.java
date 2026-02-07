package org.example.gfgs.google;

import java.util.List;

public class KadanesAlgorithm {

    public static void main(String[] args){
        int[] arr = {2,3,-8,7,-1,2,3};

        //System.out.println(bruteForce(arr));
        System.out.println(kandesAlgorithm(arr));
    }

    private static int kandesAlgorithm(int[] arr) {
        int n = arr.length;
        int max = arr[0];
        int sum=0;
        for(int i=0;i<n;i++){
            sum +=arr[i];
            max = Math.max(max,sum);
            if(sum<=0) sum=0;
        }
        return max;
    }

    /*
      Time complexity : o(n^2)
      space complexity : 0(1)i
     */
    private static int bruteForce(int[] arr) {
        int n =arr.length;
        int max = Integer.MIN_VALUE;


        for(int i=0;i<n;i++){
            int sum=0;
            for(int j=i;j<n;j++){
                sum += arr[j];
                max = Math.max(sum,max);
            }
        }
        return max;
    }
}
