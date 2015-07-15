import java.util.ArrayList;
import java.util.List;

/*
given a set of integers A = (a1, a2, a3, ... an),
find a subset A1 and A2 such that A = A1 + A2 and abs(A1-A2) is minimized
*/

public class SubsetOfIntegers {
	public static List<Integer> subset(int[] A) {
		List<Integer> res = new ArrayList<>();

		return res;
	}

	public static void main(String[] args) {
		int[] A = { 10, 1, 23, 5, 21, 1, 2, 3, 4, 10, 11, 123 };
		System.out.println(subset(A));
	}
}
