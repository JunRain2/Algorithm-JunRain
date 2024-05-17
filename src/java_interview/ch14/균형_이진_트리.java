package java_interview.ch14;

public class 균형_이진_트리 {
	boolean result;

	public boolean isBalanced(TreeNode root) {
		result = true;
		dfs(root);

		return result;
	}

	public int dfs(TreeNode node) {
		if (node == null)
			return 0;

		int left = dfs(node.left);
		int right = dfs(node.right);

		if (Math.abs(left - right) > 1) {
			result = false;
		}
		return Math.max(left, right) + 1;
	}
}
