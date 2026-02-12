### 1.  Two Pointer : 3 Sum

>Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.<br>
>Notice that the solution set must not contain duplicate triplets.


#### STEPS 
>Read the problem , Rephrase it<br>

1. integer array num -> can be positive,negative , not sorted
2. num[i] , num[j], num[k] => 0 i!=j, j!=k, i!=k , all possible triplets 
3. all possible unique triplets

>Identify the data structure (Array, String, Tree Graph)<br>

Array 
>Detect the pattern<br>

Two pointer 
>Think brute force first<br>

loop (i->n)
    loop(j->n)
        loop(k->n)
check for every point if (condition i!=j && i!=k && k!=j and sum is zero)and add it in hashset<list> for unique
>Optimize using the relevant pattern<br>

can reduce one loop by using HashMap and storing in this hashmap sum of i and j and k should be equals to -ve
loop(i->n)
 Hashmap.put(nums[i],i)
    start 0 and end n-1
        if indexes are not equal to each other
        -(nums[start] + nums[end]) if equals then increase start++ and end--
        else 
        
>Walk through small examples manually<br>


>List edge cases<br>
>Write clean code<br>
>Dry run the code with multiple examples<br>
>Explain time + space complexity



### 2 . Longest Substring Without Repeating characters
>Given a string s, find the length of the longest substring without duplicate characters.


>Read the problem , Rephrase it<br>

continus subarray from the character array , all character should be unique

>Identify the data structure (Array, String, Tree Graph)<br>

character array 
>Detect the pattern<br>

continous and need find the subarray with unique character : **SLIDING WINDOW**
>Think brute force first<br>

Brute Force approach :-
Find all subarray , check for each subarray all unique character and return the longest subarray length
Finding all subarray : O(n^2)*O(n) ~ O(n^3) 
>Optimize using the relevant pattern<br>

Instead of finding all subarray 
we can have a window of lenght r-l+1 , and will have a hashset that maintain the unique character of a window
start with l=0, r=0;
while increase the r till we find the repeating character
>Walk through small examples manually<br>

s ="pwwkew"
l=0, r=0 , n= 6
hashSet =[]

1 iteration
hashSet =[p] , r++

2 iteration
hashSet =[p,w] , r++
maxLength=2

3 iteration : find the duplicate
l++ till r value -> l=2, r=2 and removing from has 
hashSet[] =empty

4 iteration : 
add to hashset = [w] r =3 

5  add to hashSet = [w,k,e] r =5

6 now duplicate : maxLength = update(5-2+1,2) = 3


2nd example

s = abcabcbb

l=0, r=0, n=8
set =[]

set=[a] , r=0, l=0
set=[a,b] , r=1, l=0
set=[a,b,c] , r=2, l=0

maxLength = r-l+1 or set.size()
l++ remove the charAt(l)

set=[b,c] , r=2,l=1
set = [b,c,a] , r=3, l=1
maxLength = r-l+1 = 3 

remove till all become unique 
set = [c,a] , r=3, l=2

set = [c,a,b] , r=4, l=2
remove character at l  set=[a,b], l++ = l=3
remove character at l  set=[b], l++, l=4

set :[b] , r=4, l=4
length = 1 
r=5, l=5 , remove 

set [b] , r=6
r-l+1 =1 



>List edge cases<br>

0 length of stribg = ?
r reached the end ? 
>Write clean code<br>

```java
    import java.util.HashSet;

public int lengthOfLongestSubString(String s) {
    int l = 0;
    int r = 0;
    int max=0;
    HashSet<Character> set = new HashSet<>();
    for(int r=0;r<s.length();r++){
        
            while(set.contains(s.charAt(r))){
                set.remove(s.charAt(l));
                l++;
            
        }
            set.add(s.charAt(r));
            max = Math.max(max, r-l+1);
        }
    }
    
    return max;
}
```
>Dry run the code with multiple examples<br>

s = "pwwkew"

l=0, r=0 , n= 6
set=[]

iteration r=0
set: [p]

iteration r=1
set : [p,w]

iteration r=2
max = (0,2-1+1) = 2
remove l=0 character : set: [w], l=1


iternation r =3 
set[ w,k] 

iteration r =4
set = [w,k,e]

iteration r=5
max = (4-1+1 , 2 ) => 3
remve l=1 character  set = [k,e]


last =(max , hasset.size())

>Explain time + space complexity 



### 3 . Middle of linked list (https://leetcode.com/problems/middle-of-the-linked-list/)

>Read the problem , Rephrase it<br>

>Given the head of a single linked list, return the middle node of the linked list, if two middle nodes then return second middle node

>Identify the data structure (Array, String, Tree Graph)<br>

Linked List 
>Detect the pattern<br>

Fast and slow pointer
>Think brute force first<br>

find the length of the linked list , 
the node at length/2 
Time complexity : O(n) + O(n) 
>Optimize using the relevant pattern<br>

Optimize it find the middle without calculating the total length of the linked list 
move one pointer to one node and other pointer two node 

>Walk through small examples manually<br>

1->2->3->4->5
slow=1
fast=1

2nd iteration
slow=2
fast=3

3rd iteration
slow =3
fast = 5

4th iteration 
fast.next is null then return the slow 

1->2->3->4->5->6

1st iteration
slow=1
fast=1

2nd iteration
slow=2
fast=3

3rd iteration
slow=3
fast=5

4th iteration
slow =4
fast.next !=null and fast!=null -> fast =null

5th iteration
fast is null , return slow 
>List edge cases<br>

if only head=null or head.next is null return head
>Write clean code<br>

```java
    /*
        Brute force : calculating the total length and find the n/2 
     */
    private ListNode middleNodeBruteForce(ListNode node){
        int length = evaluateLength(head);
        int middle = length/2;
        int k=0;
        while(k<middle){
            head=head.next;
            k++;
        }
        return head;
    }
    private int evaluateLength(ListNode node){
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

    public ListNode middleNode(ListNode node){
        ListNode slow = node;
        ListNode fast = node;
        
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
```
>Dry run the code with multiple examples<br>


>Explain time + space complexity


### 4. find Peak Element (https://leetcode.com/problems/find-peak-element/)

> Problem Rephrase

peak element -> gerater than left and right adjacent element  [1,3,2] -> 3 is peak element , order should not be change<br>
0-indexed integer array , means need to return actual index as return from array
if there is more than 1 peak return any peak index

> Data Structure
 
Array

> Pattern detection

Array -> constraints no sorting, always need to check the its neighbour, always need to check three numbers 
3 window size -> may be sliding window

> Brute Force approach 

```java
    public int findPeakElement(int[] num){
    int n = nums.length;
    if(n==1) return 1;
    if(n==2) {
        if(num[1]>num[0]) return 1;
        else return 0;
    }
    
    for(int i=0;i<n-2;i++){
        if(num[i]<num[i+1] && num[i+1]>num[i+2]) return i+1;
    }
    //handle edge case
    if(num[0]>num[1]) return 0;
    if(num[n-2]<num[n-1]) return n-1;
    return -1;
    
}
```

Time = O(n)
space = O(1)

> As mentioned in the question that time complexity should be O(log n) time

log n one array -> binary search

>Optimal Pattern-based Approach(Binary Search)

[1, 2, 3, 1]<br>
find middle -> low = 0, high =3 , mid = 3+1/2 = 2<br>
index 2 = mid+1 and mid-1 compare find peak return peak
if not then ?

[1 ,2, 1, 3, 5, 6, 4]<br>
mid => low:0, high = 6 => 3 -> need to check for side 

```java
    public int findPeakElement(int[] nums){
    if(nums.length==1) return 0;
    int n = nums.length;
    
    if(nums[0]>nums[1]) return 0;
    if(nums[n-1]>nums[n-2]) return n-1;
    
    int start=1;
    int end=n-2;
    
    while(start<=end){
        int mid = start+(end-start)/2;
        if(nums[mid]>nums[mid-1] && nums[mid]<nums[mid+1]) return mid;
        else if(nums[mid]<mums[mid-1]) end = mid-1;
        else if (nums[mid]<nums[mid+1]) start = mid+1;
    }
    return -1;
}
```




### Prefix Sum: Subarray Sum Equals K (https://leetcode.com/problems/subarray-sum-equals-k/)

> Rephrase the questions

all possible subarrays and count of total subarray is k<br>
subarray should not be empty

> Data Structure

Array

>Pattern Detection  

Prefix sum

>Brute force approach

pick i=0, loop j=0 to 2
        j=0, sum=1
        J=1, sum=2
        j=2, sum =3 -> count =1

pick i=1 , loop j=1 to 2
        j=1, sum=1
        j=2, sum=2 -> count =2

pick i=2 , loop j=2 to 2
        j=2 ,sum 1 

```java
    int count=0;
    for(int i=0;i<n;i++){
        int sum=0;
        for(int j=i;j<n;j++){
            sum +=num[j];
            if(sum==k) count++;
        }
        
```

>why Optimize?

as it is o(2^n) -> exponential so need to modified it<br>
try to modified it 

>Optimal Pattern-approach (Prefix sum)


[1,2,3] , k =3

[1,3,6]

map => []
prefixSum=0
map => [(0,1)]

count=0;

pick i=0
prefixSum =1;
int remove = 1-3 = -2

map => [(0,1),(1,0)]

pick i=1
prefixSum = 3
int remove = 3-3 =0

map.containsKey(0) -> count=count+1 = count=1
map=>[(0,1),(1,0),(3,1)]

pick i=2
prefixSum = 6
int remove = 6-3 = 3
map.containsKey(3) -> count=2
map=>[(0,1),(1,1),(3,1),(6,1)]

```java
    import java.util.HashMap;

public int subArraySum(int[] nums, int k) {
    HashMap<Integer, Integer> map = new HashMap<>();
    int prefixSum=0;
    map.put(0,1);
    int count=0;
    for(int i=0;i<n;i++){
        prefixSum += nums[i];
        int remove = prefixSum-k;
        if(map.containsKey(remove)){
            count +=map.get(remove);
        }
        map.put(prefixSum,map.getOrDefault(prefixSum,0)+1);
        
    }
    return count;
}
```

>Intuition<br>
> The problem asks for the number of continous subarrays whose sum equals k. <br>
> if the cumulative sum up to index i is currentSum, and we want to find a subarray ending at i with sum k, we need to check if there exists a previous prefix sum (at some index j<i) such that : <br>currentSum-PreviousSum=k <br>, Rearranging this, we look for<br> PreviousSum = current-k<br>
> By Storing the frequency of all previous sums , we have seen so far in HashMap, we can instantly check how many times we have encountered the required currentSum -k.

