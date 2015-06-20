/*
http://www.geeksforgeeks.org/serialize-deserialize-n-ary-tree/
Example:
The n-ary tree below:

					A
		    B       C        D  
		  E   F           G H I J
		      K

should be serialized to:
ABE)FK)))C)DG)H)I)J)))
*/

public class NaryTreeSerializeAndDeserialize {
	public static String serialize(NaryTreeNode root) {
		StringBuilder sb = new StringBuilder();
		appendNode(root, sb);
		return sb.substring(0, sb.length() - 2);
	}

	public static void appendNode(NaryTreeNode root, StringBuilder sb) {
		if (root == null)
			return;
		sb.append(root.value + ", ");
		for (NaryTreeNode child : root.children)
			appendNode(child, sb);
		sb.append("), ");
	}

	public static NaryTreeNode deserialize(String treeStr) {
		if (treeStr.length() < 1)
			return null;
		String[] strNodes = treeStr.split(", ");
		int[] index = new int[1];
		index[0] = 0;
		NaryTreeNode root = generateTree(strNodes, index);
		return root;
	}

	private static NaryTreeNode generateTree(String[] strNodes, int[] index) {
		NaryTreeNode root = null;
		int ind = index[0];
		if (ind >= strNodes.length)
			return null;
		if (")".equals(strNodes[ind]))
			return null;
		else {
			root = new NaryTreeNode(Integer.parseInt(strNodes[ind]));
			while (true) {
				index[0]++;
				NaryTreeNode child = generateTree(strNodes, index);
				if (child != null)
					root.addChild(child);
				else
					break;
			}
		}
		return root;
	}

	public static void main(String[] args) {
		NaryTreeNode n1 = new NaryTreeNode(1);
		NaryTreeNode n2 = new NaryTreeNode(2);
		NaryTreeNode n3 = new NaryTreeNode(3);
		NaryTreeNode n4 = new NaryTreeNode(4);
		NaryTreeNode n5 = new NaryTreeNode(5);
		n1.addChild(n2);
		n1.addChild(n3);
		n1.addChild(n4);
		n1.addChild(n5);
		NaryTreeNode n6 = new NaryTreeNode(6);
		n5.addChild(n6);
		String serailizedTree = serialize(n1);
		System.out.println(serailizedTree);
		NaryTreeNode nn1 = deserialize(serailizedTree);
		System.out.println(nn1.value);
		for (NaryTreeNode child : nn1.children) {
			System.out.println(child.value);
		}
		System.out.println(serialize(nn1));
	}
}
