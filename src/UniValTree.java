/*
1. Given a binary tree, return if the tree is unival tree
2. Given a nary tree, return if the tree is unival tree
3. Given a binary tree, return the number of unval subtrees
*/

public class UniValTree {
	public boolean isTreeUnival(TreeNode root) {
		if (root == null)
			return true;
		if (root.left != null && root.val != root.left.val)
			return false;
		if (root.right != null && root.val != root.right.val)
			return false;
		return isTreeUnival(root.left) && isTreeUnival(root.right);
	}

	public boolean isNaryTreeUnival(NaryTreeNode root) {
		if (root == null)
			return true;
		for (NaryTreeNode child : root.children) {
			if (root.value != child.value)
				return false;
		}
		boolean isUniVal = true;
		for (NaryTreeNode child : root.children) {
			if (!isNaryTreeUnival(child)) {
				isUniVal = false;
				break;
			}
		}
		return isUniVal;
	}

	public int univalSubTreeCount(TreeNode root) {
		if (root == null)
			return 0;
		if (isTreeUnival(root))
			return 1;
		else
			return univalSubTreeCount(root.left)
					+ univalSubTreeCount(root.right);
	}

	public static void main(String[] args) {
		UniValTree sol = new UniValTree();
		String treeStr1 = "1, 1, 1, 1, 1, 1, 1, 1, 1";
		TreeNode root1 = TreeGenerator.deserialize(treeStr1);
		System.out.println(sol.isTreeUnival(root1));
		String treeStr2 = "1, 1, 1, 1, 2, 1, 1, 1, 1";
		TreeNode root2 = TreeGenerator.deserialize(treeStr2);
		System.out.println(sol.isTreeUnival(root2));

		NaryTreeNode nroot = new NaryTreeNode(1);
		for (int i = 1; i < 10; i++) {
			nroot.children.add(new NaryTreeNode(1));
		}
		System.out.println(sol.isNaryTreeUnival(nroot)); // true
		nroot.children.get(0).children.add(new NaryTreeNode(1));
		System.out.println(sol.isNaryTreeUnival(nroot)); // true
		nroot.children.get(0).children.add(new NaryTreeNode(2));
		System.out.println(sol.isNaryTreeUnival(nroot)); // false

		String treeStr3 = "1, 1, 2, 1, 1, 2, 2";
		String treeStr4 = "1, 1, 2, 1, 1, 2, 3";
		TreeNode root3 = TreeGenerator.deserialize(treeStr3);
		TreeNode root4 = TreeGenerator.deserialize(treeStr4);
		System.out.println(sol.univalSubTreeCount(root3));
		System.out.println(sol.univalSubTreeCount(root4));
	}
}
