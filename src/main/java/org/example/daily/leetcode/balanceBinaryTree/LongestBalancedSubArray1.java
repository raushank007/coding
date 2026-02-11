package org.example.daily.leetcode.balanceBinaryTree;

import java.util.HashMap;
import java.util.HashSet;

class Pair{
    int even;
    int odd;
    Pair(int even,int odd){
        this.even=even;
        this.odd=odd;
    }
}
class DistinctEvenOdd{
    HashMap<Integer,Pair>  pairHashMap;
    DistinctEvenOdd(){
        pairHashMap = new HashMap<>();
    }

    public HashMap<Integer,Pair> mappedIndexDistinctEvenOdd(int[] nums){
        HashSet<Integer> even = new HashSet<>();
        HashSet<Integer> odd = new HashSet<>();
        Pair evenOddPair = new Pair(0,0);
        for(int i=0;i<nums.length;i++){
            if(nums[i]%2==0){
                even.add(nums[i]);
                evenOddPair.even=even.size();
            }else{
                odd.add(nums[i]);
                evenOddPair.odd=odd.size();
            }
            pairHashMap.put(i,evenOddPair);
        }
        return pairHashMap;
    }

    public boolean isBalanced(Pair pair){
        return pair.odd== pair.even;
    }
}
public class LongestBalancedSubArray1 {

    public static void main(String[] args){
        int[] nums = {1,2,3,2};

        DistinctEvenOdd distinctEvenOdd = new DistinctEvenOdd();
        HashMap<Integer,Pair> pairHashMap = distinctEvenOdd.mappedIndexDistinctEvenOdd(nums);
        int max=0;
        for(int i=0;i<nums.length;i++){
            if(distinctEvenOdd.isBalanced(pairHashMap.get(i))){
                max  = Math.max(max,i+1);
            }
        }
        System.out.println(max);
    }

}
