package java_interview.ch17;

import java.util.PriorityQueue;

import java_interview.ch08.ListNode;

public class 리스트_정렬 {
	public ListNode sortList(ListNode head) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		while (head != null) {
			pq.offer(head.val);
			head = head.next;
		}
		ListNode result = generateList(pq);

		return result;
	}

	private ListNode generateList(PriorityQueue<Integer> pq) {
		if (pq.isEmpty()) {
			return null;
		}
		ListNode result = new ListNode(pq.poll());
		result.next = generateList(pq);
		return result;
	}

}
