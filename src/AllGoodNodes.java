/*
 We have a list of N nodes with each node pointing to one of the N nodes.
 It could even be pointing to itself. We call a node ‘good’,
 if it satisfies one of the following properties:

 * It is the tail node (marked as node 1)
 * It is pointing to the tail node (node 1)
 * It is pointing to a good node

 You can change the pointers of some nodes in order to make them all ‘good’.
 You are given the description of the nodes.
 You have to find out what is minimum number of nodes that you have to change in order
 to make all the nodes good.

 Input:
 The first line of input contains an integer number N which is the number of nodes.
 The next N lines contains N numbers,
 all between 1 and N.
 The first number, is the number of the node pointed to by Node 1;
 the second number is the number of the node pointed to by Node 2;
 the third number is the number of the node pointed to by Node 3 and so on.

 N is no larger than 1000.

 Output:
 Print a single integer which is the answer to the problem

 Sample Input 1:
 5
 1
 2
 3
 4
 5

 Sample output 1:
 4

 Explanation:
 We have to change pointers for four nodes (node #2 to node #5) to point to node #1.
 Thus 4 changes are required

 Sample input 2:
 5
 5
 5
 5
 5
 5

 Sample output 2:
 1

 Explanation:
 We have to just change node #5 to point to node #1 (tail node) which will make node #5 good.
 Since all the other nodes point to a good node (node #5), every node becomes a good node.
 */

import java.util.HashSet;

public class AllGoodNodes {
    public int minStepToAllGoodNodes(int[] nodes) {
        int n = nodes.length;
        if (n < 2) return 0;
        HashSet<Integer> comps = new HashSet<>();
        int tmp = nodes[0];
        nodes[0] = -1;
        for (int i = 1; i < n; i++) {
            int k = i + 1;
            while (k != 1 && k != nodes[k - 1]) k = nodes[k - 1];
            if (!comps.contains(k)) comps.add(k);
        }
        nodes[0] = tmp;
        return comps.size() - 1;
    }

    public static void main(String[] args) {
        AllGoodNodes sol = new AllGoodNodes();
        int[] nodes = {1, 2, 4, 1, 5, 5, 6, 6, 6}; //2 (Change 2->1, 1st 5->1 (or any node between 1-4)
        System.out.println(sol.minStepToAllGoodNodes(nodes));
    }
}
