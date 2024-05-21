package java_interview.ch14;

public class 이진_탐색_트리_합의_범위 {
	int result = 0;

	public int rangeSumBST(TreeNode root, int low, int high) {
		if (root != null) {
			rangeSumBST(root.left, low, high);
			rangeSumBST(root.right, low, high);

			if(root.val >= low && root.val <= high) {
				result += root.val;
			}
		}
		return result;
	}
}
