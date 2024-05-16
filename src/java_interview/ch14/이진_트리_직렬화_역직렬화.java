package java_interview.ch14;

import java.util.LinkedList;
import java.util.Queue;

public class 이진_트리_직렬화_역직렬화 {
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		// 예외 처리
		if (root == null)
			return "";

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		// 직렬화 결과 변수
		StringBuilder sb = new StringBuilder();
		// 0번 인덱스는 사용하지 않으며 초깃값은 루트 노드의 값
		sb.append("#," + root.val);

		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();

			// 왼쪽 자식이 널이 아니면 큐 삽입
			if (node.left != null) {
				queue.add(node.left);
				sb.append("," + node.left.val);
			} else {
				sb.append(",#");
			}
			//오른쪽 자식이 널이 아니면 큐 삽입
			if (node.right != null) {
				queue.add(node.right);
				sb.append("," + node.right.val);
			} else {
				sb.append(",#");
			}
		}
		return sb.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		// 예외 처리
		if (data.equals(""))
			return null;

		// 콤마(,) 기준으로 자른 문자열 배열 선언
		String[] nodes = data.split(",");
		// 인덱스 1번이 루트
		TreeNode root = new TreeNode(Integer.parseInt(nodes[1]));
		// 직렬화와 동일하게 BFS로 역직렬화 처리 위한 큐 선언
		Queue<TreeNode> queue = new LinkedList<>();
		// 큐에 루트부터 추가
		queue.add(root);
		// 루트를 제외하고 인덱스 2번부터 시작
		int index = 2;

		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();

			// 해당 인덱스가 #이 아니면 왼쪽 자식 노드로 설정하고 큐에 추가
			if (!nodes[index].equals("#")) {
				node.left = new TreeNode(Integer.parseInt(nodes[index]));
				queue.add(node.left);
			}
			// 좌/우 세트로 구성되므로 한 번 더 진행
			index++;
			if (!nodes[index].equals("#")) {
				node.right = new TreeNode(Integer.parseInt(nodes[index]));
				queue.add(node.right);
			}
			index++;
		}
		return root;
	}

	// Your Codec object will be instantiated and called as such:
	// Codec ser = new Codec();
	// Codec deser = new Codec();
	// TreeNode ans = deser.deserialize(ser.serialize(root));
}


