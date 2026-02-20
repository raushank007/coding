## Day 1 

### Problem 1 : **Two Pointers:** 3Sum (https://leetcode.com/problems/3sum/)

#### Rephrase problem

given array nums , find all triplets nums[i], nums[j] and nums[k] whose sum is 0. i, j, and k are different index
 > i, j and k can be random so it is okay to sort it
 
#### Data Structure
Array

#### Pattern Recognition 
sorted array and find sum -> 2 pointers using hashmap 

#### Approach

(a+b+c =0) -> -c in map and while checking a+b using two pointer ( a+b == -c)

```text
map =[]
sorted =[-4,-1,-1,0,1,2]

pick : -4, now in loop [-1,-1,0,1,2] -> check if we have 4
     L=1 , R = 5
     sorted[L] + sorted[R] = -1 +2 = 1 >4 ? No L++
     sorted[L] + sorted[R] = -1 +2 = 1 >4 ? No L++
     sorted[L] + sorted[R] = 0+2 =2>4 ? No L++
     sorted[L] + sorted[R] = 1 + 2 = 3 >4 ? No L++
     L<R -> false  out of loop

pick : -1 , now in loop [-1,-1,0,1,2]
        L=2, R=5 ,need to find 1
        sorted[L] + sorted[R] = -1+2 =1 ==1 ans =[-1,-1,2] , L++, R++
        sorted[L] + sorted[R] = -1+1 =0 >1 > No , L++
        sorted[L] + sorted[R] = 0+1 ==1 ans [-1,-1,2],[-1,0,1] L++, R--
        L<R -> false -> out of loop
        
        
so on ....
        
     


```
#### Time complexity

sort-> nlogn 
loop 2 times -> O(n^2) 

total : nlogn + O(n^2) 

#### code

```java
import java.util.ArrayList;
import java.util.HashSet;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        Set<List<Integer>> ans = new HashSet<>();
        for (int i = 0; i < n - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            int L = i + 1;
            int R = n - 1;
            int target = -nums[i];
            while (L < R) {
                int sum = nums[L] + nums[R];
                if (sum == target) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i],nums[L],nums[R]);
                    L++;R--;
                }else if(sum>target){
                    L++;
                }else{
                    R--;
                }
            }
        }
        return new ArrayList<>(ans);
    }
}
```

#### DRY RUN

```text
num =[-1,0,1,2,0,-1,-4]

|__sort->[-4,-1,-1,0,1,2]
|   |__l1 pick i=0 , num[0]=-1
|       |__ L=1, R=5, target = 1 -> [-1,-1,0,1,2]
|           |__ l2 -> check(1<5)?Yes 
|               |__ sum = 1 , 1<4  -> L++ -> L=2
|           |__ l2 -> check(2<5) ? Yes
|               |__ sum = 1, 1<4 -> L++ -> L=3
|           |__ l2 -> check(3<5) ? Yes
|               |__ sum =2 , 2<4 ?  -> L++ => L =4
|           |__ l2 -> check (4<5) ? Yes
|               |__ sum = 3 , 3<4 -> L++ -> L =5
|           |__ l2 -> check (5<5) -> false -> loop End
|   |__ l1 pick i=1  num[1]= -1 
|       |__ L=2,R=5, target =1, ->[-1,0,1,2]
|           |__ l2 -> check(2<5)? Yes
|               |__ sum = 1 -> 1==1 -> ans=[-1,-1,2] , L=3, R=4
|           |__ l2 -> check (3<4) ? Yes
|               |__ sum =1 , 1 ==1 -> ans=[-1,-1,2] , [-1,0,1] , L=4, R=3
|           |__ l2 -> check ( 4<3) ? No loop End
|   |__ l2 pick i=2 , num[2] = -1 skip 
|   |__ l3 pick i=3 , num[3] = 0
        |__ L =4 , R =5  , ->[1,2]
            |__ l2 check(4<5) ? Yes
                |__ sum = 3 , 0<3 R-- , R = 4
             |__ l2 check ( 4<4) ? No End loop

ans=[-1,-1,2] , [-1,0,1]
```

## **Sliding window:** Longest Substring without Repeating characters (https://leetcode.com/problems/longest-substring-without-repeating-characters/)

### Rephrase problem

longest substring that have unique characters
```text
example : "pwwkew"
ans : 3 
```

### Data structure 
String or array character

### pattern Recognition

work on substring -> may be sliding window

### Brute Force 

1. find all the substrings
2. check the length of length od such substring 
3. keep updating the maxLength.

```text
Example : "pwwkew"

1. All possible substrings 
pick - (0,1) -> p -> maxLength=1
pick - (0,2) -> pw -> maxLength=2
pick - (0,3) -> pww-> not 
pick - (0,4) -> pwwk-> not
pick - (0,5) -> pwwke -> not
pick - (0,6) -> pwwkew -> not

pick - (1,2) -> w -> maxLength=2
pick - (1,3) -> ww -> no ,  maxLength=2
pick - (1,4),(1,5),(1,6) -> no -> maxLength=2

pick - (2,3) -> w 
pick- (2,4) -> wk -> maxLength=2
pick - (2,5) -> wke -> maxLength=3 
pick - (2,6) -> wkew -> No, maxLength=3

pick - (3,4) -> k
pick - 3,5 -> ke
pick - 3,6 -> kew

pick - (4,5) -> e 
pick - (4,6) -> ew

pick -(5,6) -> w

ans = maxLength=3
```

### time complexity 
O(n^2)
## space complexity
O(1)

### optimize it with sliding window

```text
example = "pwwkew"

L=0, R =0 
HashSet =[]

pick : p 
HashSet is Empty() -> it is P is unique
set:[p]
L=0, 

pick :w
set[p,w]
L=0,

pick : w
set contains w 
maxLength = R-L+1 = 2-1+1 = 2
remove(L=0 character) set = [w]
again remove (L=1, character) set=[]

set=[w] , L =1 -> L=R

pick : k
set [ w, k ]

pick =e 
set [w,k,e] 

pick =w 
contains w 
maxLenghth = R-L+1 = 5-3+1 =3

```

### pseudo code

```text
L=0, maxLength=0, set=[]
for(R-> 0 to s.length())
    if(set.contains(s.charAt(R)){
        maxLength =max(maxLength, set.size());
    }
    while(!set.isEmpty() && set.contains(s.charAt(R) && L<=R){
        set.remove(s.charAt(L));
        L++;
    }
    if(set.isEmpty()) L=R;
    
    set.add(s.charAt(R));
    max= maxLength(max, set.size())
    
    
```

### DRY RUN

```text
"pwwkew"

L=0, maxLength=0, set=[]

|__pick R=0 (val:p)
|   |__ set.contains(p) ? false 
|   |__set.contains(p) while? false
|   |__ set.isEmpty() L =0;
|   |__ set=[p]
|__pick R=1 (val:w)
|    |__ set.contains(w)? false
|    |__set.contains(w) while? false
|    |__ set isEmpty() false
|    |__ set=[p,w]
|__pick R=2 (val:w)
|   |__ set.contains(w) ? true -> maxLength =2
|   |__ set.contains(w) ? true 
|       |__ remove(L=0, val:p) 
|       |__ L++ = L=1 
|   |__ set.contains(w)? true 
|       |__ remove(L=1, val:w)
|       |__ L++, L=2
|   |__ set.contains(w) ? false-> 
|   |__ set is Empty ? true -> L =2
|   |__ set =[w]
|__pick R=3 (val:k)
    |__ set.contains(k) ? false
    
    
```
>Based on observation need to update the logic of pusdo code 
> size calculation in the end
> and add empty check as well in while loop

```java
class solution{
    public int lengthOfLongestSubString(String s){
        int L=0;
        int maxLength=0;
        HashSet<Character> set = new HashSet<>();
        for(int R=0;R<s.length();R++){
            while(!set.isEmpty() && set.contains(s.charAt(i)) && L<=R){
                set.remove(s.charAt(L));
                L++;
            }
            set.add(s.charAt(R));
            maxLength = Math.max(maxLength,set.size());
        }
    }
}
```
### DRY RUN

```text
pwwkew

|__pick R=0 , val=p
|   |__ not in while
|   |__ set =[p]
|   |__ maxLength = (0,1) -> 1
|__pick R=1, val =w
|   |__ not in while
|   |__ set not empty 
|   |__ set = [p,w]
|   |__ maxLength = (1,2) -> 2
|__pick R=2 , val =w
|   |__ into while loop 
|       |__ set = [w], L=1
|   |__ into loop
|       |__ set =[] , L =2
|   |__ end of loop
|   |__ set =[w]
|   |__ maxLength = (2,1) -> 2
|__ pick R=3 , val = k
|   |__ not in while
|   |__ set = [w,k]
|   |__ maxLength = max(2,2) ->2
|__ pick R=4 , val =e
|   |__ not in while
|   |_ set =[w,k,e]
|   |__ maxLength = max(2,3) -> 3
|__ pick R=5, val =w
    |__ into while
        |__ set -> remove -> L=2, val =w ,set=[k,e]
    |__ loop end
    |__ set=[k,e,w]
    |__ maxLength =max(3,3) -> 3

Ans : 3       


```

### Time complexity 
> O(n) 
### space complexity
> O(n)


## Problem 3 : **Fast-slow:** Middle of linked list (https://leetcode.com/problems/middle-of-the-linked-list/)

### Rephrase problem
head is given of LL, find the middle node
2 middle nodes -> second one
### Data structure
Class Node for LinkedList
### Pattern recognition
fast and slow pointer 
### Brute Force
```text
1. find the length
2. return the n/2 node

1->2->3->4->5 , Length =5
n/2 -> node = 5/2 = 2  -> return node -> node 3 

1->2->3->4->5->6 . Length = 6
n/2 -> 3 -> return node => node 4

Time complexity -> o(n) + o(n/2) -> O(n) 
space complexity -> O(1) 
```
```java
class Solution{
    public ListNode middleNode(ListNode head){
        int length = evaluate(head);
        
        int k = n/2;
        while(k!=0){
            head= head.next;
            k--;
        }
        return head;
    }
    private int evaluate(ListNode head){
        int l=0;
        while(head!=null){
            head=head.next;
            l++;
        }
        return l;
    }
}
```
### DRY RUN BRUTE FORCE

```text
example -> 1->2->3->4

|__ called evaluate(head, val:1)
|   |__ l=0 , loop (head!=null), head = 1
|   |__ l=1 , head =2 , head!=null
|   |__ l=2 , head =3 , head!=null
|   |__ l=3, head =4 , head!=null
|   |__ l=4, head=null end loop
|__ k = 2
|__ loop -> k=2 ,K!=0 , head =1   
|       |__ k=1, k1=0 , head =2
|       |__ k=0  loop end
|__ return head =3 

```

### Optimize code with pattern

```text
example : 1->2->3->4->null

fast = head , 1
slow = head , 1

|__loop
|   |__ slow -> slow.next = slow :2
|   |__ fast -> fast.next.next = fast:3
|   |__ slow -> slow.next = slow : 3
|   |__ fast -> fast.next.next = null
|__ fast.next.next = null , loop end 

return slow = 3 

example : 1->2->3->4->5->null

|__ loop
|   |__ slow -> slow.next = slow :2
|   |__ fast -> fast.next.next = fast : 3
|   |__ slow -> slow.next = slow : 3
|   |__ fast -> fast.next.next = fast : 5
|   |__ slow -> slow.next = slow : 4
|   |__ fast -> fast.next=null 
|__ loop end 

 
```
> observe that need to add the check point on fast!=null, fast.next!=null loop break

```java
class Solution{
    public ListNode middle(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast!=null && fast.next!=null ){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
```
