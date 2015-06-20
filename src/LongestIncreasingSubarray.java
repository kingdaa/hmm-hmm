import java.util.ArrayList;
import java.util.List;

/*
Given an array, find out the length of longest increasing subarray
*/
public class LongestIncreasingSubarray {
	public int longestLengthOfIncreasingSubarray(int[] A) {
		if (A == null || A.length <= 1)
			return 0;
		int n = A.length;
		int start = 0, end = 1, maxLen = 0;
		while (end < n) {
			if (A[end] <= A[end - 1]) {
				if (end - 1 > start)
					maxLen = Math.max(maxLen, end - start);
				start = end;
				end++;
			} else
				end++;
		}
		if (A[end - 1] > A[start])
			maxLen = Math.max(maxLen, end - start);
		return maxLen;
	}

	public List<Integer> longestIncreasingSubarray(int[] A) {
		List<Integer> res = new ArrayList<>();
		if (A == null || A.length <= 1)
			return res;
		int n = A.length;
		int start = 0, end = 1, maxLen = 0;
		int maxStart = 0, maxEnd = 0;
		while (end < n) {
			if (A[end] <= A[end - 1]) {
				if (end - 1 > start) {
					if (end - start > maxLen) {
						maxLen = end - start;
						maxStart = start;
						maxEnd = end;
					}
				}
				start = end;
				end++;
			} else
				end++;
		}
		if (A[end - 1] > A[start] && end - start > maxLen) {
			maxLen = end - start;
			maxStart = start;
			maxEnd = end;
		}
		if (maxLen > 0) {
			for (int i = maxStart; i < maxEnd; i++)
				res.add(A[i]);
		}
		return res;
	}

	public static void main(String[] args) {
		LongestIncreasingSubarray sol = new LongestIncreasingSubarray();
		int[] A1 = { 2, 1, 2, 1, 0, 1, 2, -1, 3, 4, 5, 6, 10, 11 };
		int[] A2 = { 1, 2, 3, 4, 5 };
		int[] A3 = { 5, 4, 3, 2, 1 };
		int[] A4 = { -1, -1, -1 };
		System.out.println(sol.longestLengthOfIncreasingSubarray(A1));
		System.out.println(sol.longestIncreasingSubarray(A1));
		System.out.println(sol.longestLengthOfIncreasingSubarray(A2));
		System.out.println(sol.longestIncreasingSubarray(A2));
		System.out.println(sol.longestLengthOfIncreasingSubarray(A3));
		System.out.println(sol.longestIncreasingSubarray(A3));
		System.out.println(sol.longestLengthOfIncreasingSubarray(A4));
		System.out.println(sol.longestIncreasingSubarray(A4));
	}
}
