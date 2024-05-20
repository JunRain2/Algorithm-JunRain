package java_interview.ch14;

public class 정렬된_배열의_이진_탐색_트리_변환 {
	public TreeNode sortedArrayToBST(int[] nums) {
		return dfs(nums, 0, nums.length - 1);
	}

	TreeNode dfs(int[] nums, int start, int end) {
		if (start > end) return null;

		int mid = (start + end) / 2;
		TreeNode root = new TreeNode(nums[mid]);

		root.left = dfs(nums, start, mid - 1);
		root.right = dfs(nums, mid + 1, end);

		return root;
	}
}
