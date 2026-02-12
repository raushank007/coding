package org.example.Feb11;

import java.util.*;
class ListNode{
    int val;
    ListNode next;
    ListNode(){}
    ListNode(int val){
        this.val = val;
    }
    ListNode(int val, ListNode next){
        this.val=val;
        this.next=next;
    }
}
public class Feb12 {
    public static void main(String[] args){
        int[] arr = {-1,0,1,2,-1,-4};

        //1. 3 Sum -Brute Force
        List<List<Integer>> bruteForce3Sum = threeSumBruteForce(arr);
        System.out.println("bruteForceThreeSum: "+ bruteForce3Sum);

        //1 . 3 Sum - Two pointer
        List<List<Integer>> OptimizedThreeSum = threeSumOptimized(arr);
        System.out.println("OptimizedThreeSum: "+ OptimizedThreeSum);

        String s = "abcabcbb";
        int longestSubStringWithoutRepeating = slidingWindowApproach(s);
        System.out.println("Longest substring without repeating: "+longestSubStringWithoutRepeating);

        /*
         create linked list
         */
        int[] linkedListData = {1,2,3,4,5,6,7};
        ListNode dummy =new ListNode();
        ListNode head = dummy;
        for(int i=0;i<linkedListData.length;i++){
            ListNode  temp = new ListNode(linkedListData[i]);
            dummy.next = temp;
            dummy=dummy.next;
        }
        ListNode middleBruteForce = middleNodeBruteForce(head.next);
        System.out.println("middle of the linked list:" +middleBruteForce);
        ListNode middleFastAndSlow =middleNode(head.next);
        System.out.println("middle of the linked list:"+middleFastAndSlow);


        /*
            Binary Search :Find Peak Element
         */
        int[] pE = {1,2,3,1};

        int findPeakElement = binarySearchPeakElement(pE);
        System.out.println("Peak element using binary Search: "+findPeakElement);

    }

    private static int binarySearchPeakElement(int[] num) {
        int n = num.length;
        if(n==1) return 0;
        if(num[0]>num[1]) return 0;
        if(num[n-1]>num[n-2]) return n-1;

        int low = 1;
        int high = n-2;

        while(low<=high){
            int mid = low+(high-low)/2;
            if(num[mid+1]<num[mid] && num[mid]>num[mid-1]) return mid;
            else if(num[mid+1]>num[mid]) low = mid+1;
            else if(num[mid-1]>num[mid]) high = mid-1;
        }
        return -1;
    }

    /*
        Brute force : calculating the total length and find the n/2
     */
    private static ListNode middleNodeBruteForce(ListNode head){
        int length = evaluateLength(head);
        int middle = length/2;
        int k=0;
        while(k<middle){
            head=head.next;
            k++;
        }
        return head;
    }
    private static int evaluateLength(ListNode node){
        int length=0;
        while(node!=null){
            node=node.next;
            length++;
        }
        return length;
    }


    /*
            remove the length calculation to reduce the iterations on linkedlist
            Fast and slow pointer approach
     */

    public static  ListNode middleNode(ListNode node){
        ListNode slow = node;
        ListNode fast = node;

        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static int slidingWindowApproach(String s) {

            int l = 0;
            int r;
            int max=0;
            HashSet<Character> set = new HashSet<>();
            for(r=0;r<s.length();r++){
                    while(set.contains(s.charAt(r))){
                        set.remove(s.charAt(l));
                        l++;
                    }

                    set.add(s.charAt(r));
                    max = Math.max(max, r-l+1);

            }

            return max;

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
