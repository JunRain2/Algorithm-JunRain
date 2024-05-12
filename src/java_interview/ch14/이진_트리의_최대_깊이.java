package java_interview.ch14;

import java.util.LinkedList;
import java.util.Queue;

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

	// BFS
	public int maxDepthBFS(TreeNode root) {
		// 예외 처리
		if(root == null) return 0;
		// BFS 진행을 위한 큐 선언, 큐는 인터페이스이며 자료형 선언은 LinkedList 사용
		Queue<TreeNode> queue = new LinkedList<>();
		// root 부터 큐에 삽입
		queue.add(root);
		// 결과로 리턴하 깊이 변수 선언
		int depth = 0;

		// 큐가 모두 비워질 때까지 반복
		while(!queue.isEmpty()) {
			depth++;
			// 큐 크기 계산, 이 값은 해당 깊이의 모든 노드 수와 일치
			int q_size = queue.size();
			for(int i = 0; i < q_size; i++) {
				// 가장 먼저 삽입된 노드(가장 왼쪽)부터 추출
				TreeNode cur = queue.poll();
				// 왼쪽 자식 노드가 있다면 큐에 삽입
				if(cur.left != null) {
					queue.add(cur.left);
				}
				// 오른쪽 자식 노드가 있다면 큐에 삽입
				if(cur.right != null) {
					queue.add(cur.right);
				}
			}
		}
		return depth;
	}

	// 내가 푼 풀이보다 메모리를 더 적게 소비
	public int maxDepthDfs(TreeNode root) {
		// 더 이상 존재하지 않는 노드라면 0 리턴
		if(root == null) return 0;
		// 왼쪽 노드에 깊이만큼 +1 처리된 값이 리턴
		int left = maxDepthDfs(root.left);
		// 오른쪽 노드에 깊이만큼 +1 처리된 값이 리턴
		int right = maxDepthDfs(root.right);
		// 왼쪽/오른쪽 노드 중 큰 값에 +1하여 리턴
		return Math.max(left, right) + 1;
	}
}
// https://leetcode.com/problems/maximum-depth-of-binary-tree/submissions/1256204415/