package java_interview.ch14;

public class 이진_트리의_최대_깊이 {
	public int maxDepth(TreeNode root) {
		return dfs(root, 0);
	}

	public int dfs(TreeNode node, int count) {
		if (node == null) {
			return count;
		}

		count++;
		int left = dfs(node.left, count);
		int right = dfs(node.right, count);

		return Math.max(left, right);
	}
}
// https://leetcode.com/problems/maximum-depth-of-binary-tree/submissions/1256204415/