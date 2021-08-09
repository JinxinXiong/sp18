#CS61B proj1a

## 1. LinkedListDeque

use sentfront and sentback

<img src="1.png" alt="image-20210805222021895" style="zoom:50%;" />

## 2. ArrayDeque

Use nextfirst and next last

when creating an empty deque: set nextlast to 0, nextfirst to the end of the array

when resizing the duque: set nextfirst to the end of the array, copy the element one by one from 0, and then set nextlast to the corresponding place after all elements have been set.

![image-20210809153220505](2.png)