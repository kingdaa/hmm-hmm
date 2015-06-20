/*
Moving window min:

Input: A[N], K.
Output: B[N], B = min(A, A[i+1], A[i+2],..., A[i+K-1]).

Sample Input:
A[7] = {7, 5, 4, 3, 6, 7, 8}, K = 3
B[7] = {4, 3, 3, 3, 6, 7, 8}
*/

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

class ValueIndexPair implements Comparable<ValueIndexPair> {
    Integer value;
    Integer index;

    ValueIndexPair(Integer value, Integer index) {
        this.value = value;
        this.index = index;
    }

    @Override
    public int compareTo(ValueIndexPair o) {
        return Integer.compare(this.value, o.value);
    }
}


public class MinimumInSlidingWindow {
    public void findMinsInSlidingWindowPQ(int[] A, int[] B, int n, int k) {
        PriorityQueue<ValueIndexPair> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++)
            pq.add(new ValueIndexPair(A[i], i));
        for (int i = k; i < n + k - 1; i++) {
            ValueIndexPair top = pq.peek();
            B[i - k] = top.value;
            while (top.index <= i - k) {
                pq.remove();
                top = pq.peek();
            }
            if (i < n)
                pq.add(new ValueIndexPair(A[i], i));
        }
        B[n - 1] = pq.peek().value;
    }

    public void findMinsInSlidingWindow(int[] A, int[] B, int n, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && A[i] <= A[deque.peekLast()])
                deque.removeLast();
            deque.addLast(i);
        }
        for (int i = k; i < n + k; i++) {
            System.out.println(deque);
            B[i - k] = A[deque.peekFirst()];
            while (!deque.isEmpty() && i - k >= deque.peekFirst())
                deque.removeFirst();
            if (i < n) {
                while (!deque.isEmpty() && A[i] <= A[deque.peekLast()])
                    deque.removeLast();
                deque.addLast(i);
            }
        }
    }

    public static void main(String[] args) {
        int[] A = {7, 5, 4, 3, 6, 7, 8};
        int n = A.length;
        int[] B1 = new int[n];
        int[] B2 = new int[n];
        MinimumInSlidingWindow sol = new MinimumInSlidingWindow();
        sol.findMinsInSlidingWindowPQ(A, B1, n, 3);
        sol.findMinsInSlidingWindow(A, B2, n, 3);
        for (int a : B1) {
            System.out.print(a + " ");
        }
        System.out.println();
        for (int a : B2) {
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
