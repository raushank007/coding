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


