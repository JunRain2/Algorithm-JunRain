package java_interview.ch14;

public class 이진_탐색_트리를_더_큰_수_합계_트리로 {
	int sum;

	public TreeNode bstToGst(TreeNode root) {
		sum = 0;

		reverseDfs(root);

		return root;
	}

	public void reverseDfs(TreeNode root) {
		if (root == null)
			return;

		reverseDfs(root.right);
		sum += root.val;
		root.val = sum;
		reverseDfs(root.left);
	}
}
