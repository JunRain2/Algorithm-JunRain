package java_interview.ch08;

public class ReverseList {
	// 재귀
	// 다음 노드 next와 현재 노드 node를 파라미터로 하여 계속해서 재귀 호출
	// node.next에는 이전 prev 리스트를 계속 연결해주면서 node가 null이 될 때까지 재귀 호출하면 마지막에는 백트래킹되면서 연결 리스트가 거꾸로 연결
	// 맨 처음에 리턴된 prev는 뒤집힌 연결 리스트의 첫 번째 노드가 된다.
	public ListNode reverse(ListNode node, ListNode prev) {
		// 현재 노드인 node가 null이면 리턴
		if (node == null) {
			return prev;
		}
		// 현재 노드의 다음 노드 미리 지정
		ListNode next = node.next;
		// 현재 노드의 다음으로 이전 노드 지정
		node.next = prev;
		// 다음 노드와 현재 노드를 피라미터로 하여 재귀 호출
		// node.next = prev 된 노드가 기입
		return reverse(next, node);
	}

	public ListNode reverseListRecursive(ListNode head) {
		return reverse(head, null);
	}

	// 반복
	public ListNode reverseListIterative(ListNode head) {
		ListNode prev = null, node = head;
		while (node != null) {
			ListNode next = node.next;
			node.next = prev;
			prev = node;
			node = next;
		}
		return prev;
	}
}
