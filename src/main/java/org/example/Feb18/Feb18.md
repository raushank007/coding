### Container With Most Water (https://leetcode.com/problems/container-with-most-water/)

>Rephrase the problem

give height of container at each index, maximum amount of water store between heights

> Data Structure 

Array

> Brute Force

```text
                0 1 2 3 4 5 6 7 8
height =      [1,8,6,2,5,4,8,3,7]
heightEnd[9][2] = [[8,6],[8,6],[8,6],[8,6],[8,6],[8,6],[8,6][7,8],[7,8]]

all possible two combinations 

output = 49

index 1 to index 8 -> min(8,7) -> 7 * (8-1) = 49


```

```java
    class solution{
        public int maxArea(int[] height){
            int n = height.length;
            for(int i=0;i<n;i++){
                for(int j=i;j<n;j++){
                    max = Math.max(min(height[i],height[j])*[i-j])
                }
            }
        }
}
```

>Optimize with 2 pointers

area = width*height

we need to take the smaller height between left and right m because if we calculate based on the taller height, the water would overflow from the container<br>

How can we judge? where pointer will move<br>
We want to keep the taller height between left and right because there is a possibility that we will get max area with the taller height.<br>

```java
    class Solution {
    public int maxArea(int[] height){
        int L=0;
        int n = height.length;
        int R=n-1;
        
        int max = 0;
        
        while(R>L){
            int area = Math.max(height[L],height[R])*(R-L);
            max = Math.max(max,area);
            
            if(height[R]>height[L]){
                R--;
            }else{
                L++;
            }
        }
        return max;
    }
}
```