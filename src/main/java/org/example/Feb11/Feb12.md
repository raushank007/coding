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