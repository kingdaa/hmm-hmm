/*
    Merge K sorted Arrays to one array.
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class HeapNode {
    int value;
    int array;
    int curIndex;

    HeapNode(int value, int array, int curIndex) {
        this.value = value;
        this.array = array;
        this.curIndex = curIndex;
    }
}

class HeapNodeComparator implements Comparator<HeapNode> {
    @Override
    public int compare(HeapNode o1, HeapNode o2) {
        return o1.value <= o2.value ? -1 : 1;
    }
}

public class MergeKSortedArrays {
    public int[] mergeKSortedArrays(List<int[]> list) {
        int k = list.size();
        int size = 0;
        for (int[] arr : list) {
            if (arr == null || arr.length < 1) k--;
            size += arr.length;
        }
        int[] res = new int[size];
        mergeKSortedArraysHelper(list, k, res);
        return res;
    }

    private void mergeKSortedArraysHelper(List<int[]> list, int k, int[] res) {
        PriorityQueue<HeapNode> heap = new PriorityQueue<>(k, new HeapNodeComparator());
        for (int i = 0; i < list.size(); i++) heap.add(new HeapNode(list.get(i)[0], i, 0));
        int i = 0;
        while (!heap.isEmpty()) {
            HeapNode top = heap.remove();
            res[i++] = top.value;
            if (list.get(top.array).length > top.curIndex + 1)
                heap.add(new HeapNode(list.get(top.array)[top.curIndex + 1], top.array, top.curIndex + 1));
        }
    }

    public static void main(String[] args) {
        int[] A1 = {1, 3, 1000, 1234};
        int[] A2 = {2, 31, 100, 121, 123, 199, 1234, 123123};
        int[] A3 = {1000, 3111, 11000, 12234, 1231233};
        int[] A4 = {1, 31111, 111000, 1234123, 213123213, 1231231231};
        List<int[]> list = new ArrayList<>();
        list.add(A1);
        list.add(A2);
        list.add(A3);
        list.add(A4);
        MergeKSortedArrays sol = new MergeKSortedArrays();
        int[] res = sol.mergeKSortedArrays(list);
        for (int i : res) System.out.print(i + " ");
        System.out.println();
    }
}
