package java_interview.ch08;

public class SwapPairs {
	// 값만 교환
	public ListNode swapPairsValue(ListNode head) {
		ListNode node = head;

		while (node != null && node.next != null) {
			int tmp;
			tmp = node.val;
			node.val = node.next.val;
			node.next.val = tmp;
			node = node.next.next;
		}
		return head;
	}

	// 내가 푼 문제
	/**
	 * 맨 앞만 생각하고, 뒷 부분 연결고리 부분을 생각 못함
	 * 1 -> 2 -> 3 부분 만 생각
	 * 1 -> 2 -> 3 -> 4 에서 3 -> 4 가 바뀌면 그 뒤에 연결 고리도 바뀐다는 것을 간과함.
	 */
	public ListNode swapPairs(ListNode head) {
		ListNode present = head;

		while (present != null && present.next != null) {
			ListNode next = present.next.next;
			present.next.next = present;
			present.next = next;
			present = present.next;
		}

		if (head.next == null) {
			return head;
		}
		return head.next;
	}

	// 루프
	/**
	 * 모든 문제를 같게 풀기 위해 앞에 임시 노드를 추가.
	 * 즉 뒤 페어와 앞 페어 둘 다 고려
	 */
	public ListNode swapPairsLoop(ListNode head) {
		// 값을 계산할 임시 노드 선언
		ListNode node = new ListNode(0);
		// 임시 노드를 첫 번째 노드로 선언
		ListNode root = node;
		// 다음 노드는 첫 번째 노드로 지정
		node.next = head;
		// 다음 노드와 다음 다음 노드가 있으면 반복
		while (node.next != null && node.next.next != null) {
			ListNode a = node.next;
			ListNode b = node.next.next;
			a.next = b.next;
			node.next = b;
			b.next = a;
			node = node.next.next;
		}

		return root.next;
	}

	// 재귀
	public ListNode swapPairsRecursive(ListNode head) {
		if (head != null && head.next != null) {
			ListNode p = head.next;
			head.next = swapPairs(head.next.next);
			p.next = head;
			return p;
		}
		return head;
	}
}
