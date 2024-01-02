package java_interview.ch08;

public class ReverseList {
	// 재귀
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
