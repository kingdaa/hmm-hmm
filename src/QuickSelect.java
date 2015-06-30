public class QuickSelect {
	private int select(int[] nums, int k) {
		if (k < 0 || k >= nums.length)
			throw new IndexOutOfBoundsException(
					"Selected element out of bounds");
		int left = 0, right = nums.length - 1;
		while (left < right) {
			int i = partition(nums, left, right);
			if (i > k)
				right = i - 1;
			else if (i < k)
				left = i + 1;
			else
				return nums[i];
		}
		return nums[left];
	}

	private int partition(int[] nums, int start, int end) {
		int left = start, right = end + 1;
		int v = nums[start];
		while (true) {
			while (nums[++left] < v)
				if (left == end)
					break;
			while (v < nums[--right])
				if (right == start)
					break;
			if (left >= right)
				break;
			swap(nums, left, right);
		}
		swap(nums, start, right);
		return right;
	}

	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public static void main(String[] args) {
		int[] A1 = { 2, 4, 1, 3 };
		int[] A2 = { 1, 2, 3, 2, 1, 10 };
		QuickSelect sol = new QuickSelect();
		for (int i = 0; i < A1.length; i++)
			System.out.println(i + ": " + sol.select(A1, i));
		for (int i = 0; i < A2.length; i++)
			System.out.println(i + ": " + sol.select(A2, i));
	}
}
