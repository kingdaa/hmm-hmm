/*
Given a binary tree, find the longest increasing path length from root to leaf
*/

public class LongestIncreasingPathInTree {
	public int longestIncreasingPathLength(TreeNode root) {
		if (root == null)
			return 0;
		boolean goLeft = true, goRight = true;
		if (root.left == null || root.left.val <= root.val)
			goLeft = false;
		if (root.right == null || root.right.val <= root.val)
			goRight = false;
		if (goLeft && !goRight)
			return 1 + longestIncreasingPathLength(root.right);
		else if (!goLeft && goRight)
			return 1 + longestIncreasingPathLength(root.right);
		else if (goLeft && goRight)
			return 1 + Math.max(longestIncreasingPathLength(root.left),
					longestIncreasingPathLength(root.right));
		else
			return 1;
	}

	public static void main(String[] args) {
		LongestIncreasingPathInTree sol = new LongestIncreasingPathInTree();
		String treeStr1 = "1,2,3,1,1,4,1,5,6,7,8,9,10,2,2";
		TreeNode root1 = TreeGenerator.deserialize(treeStr1);
		System.out.println(sol.longestIncreasingPathLength(root1));
	}

}
