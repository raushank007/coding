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