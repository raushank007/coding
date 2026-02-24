# Problem 1 : **Two Pointers:** Container With Most Water (https://leetcode.com/problems/container-with-most-water/)

## Rephrase problem

```text
height =[1,8,6,2,5,4,8,3,7]
output:49
```
find the max area between two heights 

## Data Structure
Array
## Pattern Recognition
Two pointers ?

## Brute force

```text
height =[1,8,6,2,5,4,8,3,7]
output:49

Area = height*width
height are the values of index 
width are the difference between the index 

difference is index is larger and the min(hindex1,hindex2)

width are sorted -> can use two pointer 

L=0, R=8

pick L=0,(val:1) R=8 (val:7)
area = (8-0)*min(7,1) -> 8*1 = 8

7>1 -> increase L
pick L=1, (val:8) R=8(val:7)
area =(8-1) *min(8,7) = 7*7 =49

8>7 -> decrease R
pick L=1 (val:8) , R=7(val:3)
area = (7-1)*min(8,3) = 6*3=18

8>3 -> decrease R
pick L=1 ( val:8) , R=6(val:8)
area = (6-1)*min(8,8) = 5*8 = 40


```
##  code
```text

class Solution{
    public int maxArea(int[] height){
        int L=0;
        int R = height.length-1;
        int max=0;
        while(L<R){
            int area = (R-L)*(Math.min(height[L],height[R]);
            max=Math.max(area,max);
            
            if(height[R]>height[L]) L++;
            else R--;
        }
        return max;
    }
}


```

## DRY RUN
```text
height =[6,2,5,4]

pick L=0(val:6), R=3(val:4) , max=0
|__ while check 0<3 ? true
|   |__ area = (3-0)*min(6,4) -> 12
|   |__ max = 12
|   |__ 4>6? No R-- -> R = 2
|__ while check 0<2? true
|   |__ area = (2-0)*min(6,5) -> 2*5 = 10
|   |__ max = 12
|   |__ 5>6? No -> R-- -> 1 val(2)
|__ while check (0<1) ? true
    |__ area = (1-0)*min(6,2) -> 2
    |__ max =12
    |__ 1>6 ? No R-- -> 0 
|__ while check (0<0) ? false

max = 1

```

## Time complexity and Space complexity
 Time complexity = O(n) , space complexity = O(1)