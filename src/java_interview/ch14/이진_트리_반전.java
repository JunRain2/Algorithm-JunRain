package java_interview.ch14;

public class 이진_트리_반전 {
	public TreeNode invertTree(TreeNode root) {
		dfs(root);

		return root;
	}

	public TreeNode dfs(TreeNode node) {
		if(node == null) return null;

		TreeNode left = dfs(node.left);
		TreeNode right = dfs(node.right);

		TreeNode tmp = left;

		node.left = right;
		node.right = tmp;

		return node;
	}
}
