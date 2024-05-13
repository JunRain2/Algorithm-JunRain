package java_interview.ch14;

public class 가장_긴_동일_값의_경로 {
	int result = 0;

	public int dfs(TreeNode node) {
		// 예외 처리 및 존재하지 않는 노드일 때 거리 0 리턴
		if (node == null)
			return 0;

		// 완쪽, 오른쪽의 각 리프 노드까지 재귀 DFS
		int left = dfs(node.left);
		int right = dfs(node.right);

		// 현재 노드가 왼쪽 자식 노드와 동일한 경우 왼쪽 거리 1 증가
		if (node.left != null && node.left.val == node.val)
			left++;
		// 다르면 거리를 0으로 초기화
		else
			left = 0;
		// 현재 노드가 오른쪽 자식 노드와 동일한 경우 오른쪽 거리 1 증가
		if(node.right != null && node.right.val == node.val)
			right++;
		// 다르면 거리를 0으로 초기화
		else
			right = 0;

		// 왼쪽/오른쪽 자식 노드 간 거리의 합 최댓값이 최종 결과가 된다.
		this.result = Math.max(this.result, left + right);
		// 왼쪽/오른쪽 녿 중 큰 값을 리턴
		return Math.max(left, right);
	}

	public int longestUnivaluePath(TreeNode root) {
		dfs(root);
		return this.result;
	}
}
