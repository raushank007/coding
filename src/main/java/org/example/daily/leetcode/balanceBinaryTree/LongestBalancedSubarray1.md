# 3719 Longest Balanced Subarray 1

**Given : integer array nums<br>Subarray is called balanced of distinct even numbers in the subarray is equal to the number of distinct odd numbers<br>**
**Return the length of the longest balanced subarray**

### Example 1
```java
int[] nums = {2,5,4,3};
longest subarray: 4
```

## intuition

**Brute force**
1. find every subarray
2. count even and odd , if both is equal then update the longest length

### pseudo code
```java
int max=Integer.MIN_VALUE;
for(int i=0;i<n;i++){
    for(int j=i;j<n;j++){
        if(isBalancedSubarray(i,j,nums)){
            max = Math.max(j-i,max);
        }
        }
        }
return max;

private isBalancedSubarray(int i,int j,int[] nums){
    int even=0;
    int odd=0;
    for(int i=0;i<n;i++){
        if(nums[i]%2==0) even++;
        else odd++;
    }
    return even==odd;
}
```

> check with the distinct subarray , verify all given input output


### Brute force

```java
    import java.util.ArrayList;

class solution {
    public int longestBalanced(int[] nums) {
        int max = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isBalancedSubarray(i, j, nums)) {
                    max = Math.max(j - i + 1, max);
                }
            }
        }
        return max;
    }

    private boolean isBalancedSubarray(int i, int j, int[] nums) {
        int odd = 0;
        int even = 0;
        List<Integer> list = new ArrayList<>();
        for(int k=i;k<-j;k++){
            list.add(nums[k]);
        }
        
        List<Integer> distinct = list.stream().distinct().toList();
        for(int k=0;k<distinct.size();i++){
            if(distinct.get(k)%2==0) even++;
            else odd++;
        }
        return even==odd;
        
    }
}
```

**Time complexity : O(n^3) , space complexity : O(n)**

### optimise approach
**Sliding window**
store even and odd count at evey index , using hashmap<index, Pair> and hashset for uniqueness check
and then use sliding window to find the max


