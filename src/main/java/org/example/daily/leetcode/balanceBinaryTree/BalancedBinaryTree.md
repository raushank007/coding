# Balanced Binary Tree

**height balanced BT : the depth of the two subtree is not more than 1**

## Check input output 

### Example 1 
````mermaid
graph TD
    A((3)) --> B((9))
    A --> C((20))
    C --> F((15))
    C --> G((7))
````

**All possible subtrees**
1. SubTree 1 : full tree
    ````mermaid
   graph TD
    A((3)) --> B((9))
    A --> C((20))
    C --> F((15))
    C --> G((7))
   ````
2. SubTree 2 : Left branch 
    ````mermaid
   graph TD
   B((9))
   ````
3. SubTree 3 : Right branch
    ````mermaid
   graph TD
   C((20)) --> F((15))
   C-->G((7))
   
4. SubTree 4 : left of 20
    ````mermaid
   graph TD
   F((15))
   ````
5. Subtree 5 : right of 20
    ````mermaid
   graph TD
   G((7))
   ````