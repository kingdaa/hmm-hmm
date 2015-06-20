/**
 * Created by King on 6/2/15.
 */
// This is the text editor interface.
// Anything you type or change here will be seen by the other person in real time.
/*
Given a list of numbers, determine whether it can represent the pre-order traversal list of a binary search tree (BST).

Input
The first line contains the number of test cases, T. T lines follow, consisting of two lines each.
The first line of each test case contains the number of nodes in the tree, N. In next line there will a list of N unique numbers, where each number is from the range [1, N].

Output
For each test case, print the string “YES” if there exists a BST whose pre-order traversal is equal to the list, otherwise print the string “NO” (without quotes, preserving capitalization).

Constraints
1 ≤ T ≤ 10
1 ≤ N ≤ 100

Sample Input
5
3
1 2 3
3
2 1 3
6
3 2 1 5 4 6
4
1 3 4 2
5
3 4 5 1 2

Sample Output
YES
YES
YES
NO
NO

Explanation
•	The first three cases are from the above examples.
•	In case 4, after encountering the 3, the 4 tells us we are on the right sub-tree, which means no values smaller than 3 are allowed any longer. So when we see the 2 we know the list is invalid.
•	Similarly, in case 5, after encountering the 3, the 4 and 5 tell us we are on the right sub-tree, so the subsequent encounter of values 2 and 1, which belong in the left sub-tree, tells us that the list is not valid as a pre-order traversal of a BST.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Counter {
    int i;

    Counter(int i) {
        this.i = i;
    }

    public void increment() {
        i += 1;
    }

    public int get() {
        return i;
    }
}

public class BST {
    TreeNode root;
    int count = 0;
    // BST(int value){
    //     root = new TreeNode(value);
    // }

    public void construct(List<Integer> preOrder) {
        Counter index = new Counter(0);
        root = constructFromPreorderHelper(preOrder, index, Integer.MIN_VALUE, Integer.MAX_VALUE, preOrder.size());
    }

    public TreeNode constructFromPreorderHelper(List<Integer> preOrder, Counter index, int min, int max, int size) {
//        System.out.println(index + " " + min + " " + max);
        if (index.get() >= size) return null;
        int currentNum = preOrder.get(index.get());
        TreeNode root = null;
        if (currentNum > min && currentNum < max) {
            root = new TreeNode(currentNum);
//            System.out.println("root = " + currentNum);
            index.increment();
            count++;
            if (index.get() < size) {
                root.left = constructFromPreorderHelper(preOrder, index, min, currentNum, size);
                root.right = constructFromPreorderHelper(preOrder, index, currentNum, max, size);
            }
        }
        return root;
    }

    public boolean validBST(List<Integer> preOrder) {
        construct(preOrder);
        return count == preOrder.size();
    }

    /*
    Test Input:
    5
    3
    1 2 3
    3
    2 1 3
    6
    3 2 1 5 4 6
    4
    1 3 4 2
    5
    3 4 5 1 2
    */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                list.add(sc.nextInt());
            }
            BST bst = new BST();
            System.out.println(bst.validBST(list));
        }

    }
}
