import java.util.LinkedList;
import java.util.Queue;

/* 
 Provides function that generate a binary tree from given string
 Example:
 Input [1, 2, #, 3, 4, #, #, #, #, 5, 6]
 Return a tree like
 		1
 	  /   
 	 2
   /   \
  3     4
       / \
      5   6
*/

class TreeNodeWrapper {
	TreeNode node;
	boolean isNode;

	public TreeNodeWrapper(TreeNode node, boolean isNode) {
		this.node = node;
		this.isNode = isNode;
	}
}

public class TreeGenerator {
	public static TreeNode deserialize(String s) {
		String[] strNodes = s.split(",");
		int n = strNodes.length;
		if (n < 1)
			return null;
		TreeNode root = new TreeNode(Integer.parseInt(strNodes[0]));
		generateChildNodes(root, strNodes, 1, n);
		return root;
	}

	public static String serialize(TreeNode root) {
		if (root == null)
			return "";
		StringBuilder sb = new StringBuilder();
		int numInLevel = 1, thisLevel = 1, nextLevel = 0;
		Queue<TreeNodeWrapper> queue = new LinkedList<>();
		TreeNodeWrapper rootWrapper = new TreeNodeWrapper(root, true);
		queue.add(rootWrapper);
		while (!queue.isEmpty() && thisLevel > 0) {
			for (int i = 0; i < numInLevel; i++) {
				TreeNodeWrapper cur = queue.remove();
				if (cur.isNode)
					sb.append(cur.node.val + ", ");
				else
					sb.append("#, ");
				if (cur.isNode && cur.node.left != null) {
					nextLevel++;
					queue.add(new TreeNodeWrapper(cur.node.left, true));
				} else
					queue.add(new TreeNodeWrapper(null, false));
				if (cur.isNode && cur.node.right != null) {
					nextLevel++;
					queue.add(new TreeNodeWrapper(cur.node.right, true));
				} else
					queue.add(new TreeNodeWrapper(null, false));
			}
			numInLevel *= 2;
			thisLevel = nextLevel;
			nextLevel = 0;
		}
		return sb.substring(0, sb.length() - 2);
	}

	private static void generateChildNodes(TreeNode root, String[] strNodes,
			int index, int n) {
		if (root == null || index > n / 2)
			return;
		String leftStrNode = strNodes[index * 2 - 1].trim();
		if (!"#".equals(leftStrNode)) {
			root.left = new TreeNode(Integer.parseInt(leftStrNode));
			generateChildNodes(root.left, strNodes, index * 2, n);
		}
		if (index * 2 < n) {
			String rightStrNode = strNodes[index * 2].trim();
			if (!"#".equals(rightStrNode)) {
				root.right = new TreeNode(Integer.parseInt(rightStrNode));
				generateChildNodes(root.right, strNodes, index * 2 + 1, n);
			}
		}
	}

	public static void main(String[] args) {
		String treeStr = "1, 2, #, 3, 4, #, #, #, #, 5, 6, #, #, #, #";
		TreeNode root = TreeGenerator.deserialize(treeStr);
		System.out.println(root.val);
		System.out.println(root.left.val);
		System.out.println(root.left.left.val);
		System.out.println(root.left.right.val);
		System.out.println(root.left.right.left.val);
		System.out.println(root.left.right.right.val);
		System.out.println(TreeGenerator.serialize(root));
	}
}
