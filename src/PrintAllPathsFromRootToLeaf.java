import java.util.LinkedList;

/**
 * Created by King on 4/19/15.
 */
public class PrintAllPathsFromRootToLeaf {

    public void printAllPaths(TreeNode root) {
        LinkedList<Integer> cache = new LinkedList<Integer>();
        dfs(root, cache);
    }

    public void dfs(TreeNode root, LinkedList<Integer> cache) {
        cache.add(root.val);
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (Integer i : cache) {
                sb.append(i);
                sb.append("->");
            }
            sb.setLength(sb.length() - 2);
            System.out.println(sb.toString());
        } else {
            if (root.left != null) dfs(root.left, cache);
            if (root.right != null) dfs(root.right, cache);
        }
        cache.removeLast();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(8);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node3.left = node7;
        PrintAllPathsFromRootToLeaf sol = new PrintAllPathsFromRootToLeaf();
        sol.printAllPaths(root);
    }
}
