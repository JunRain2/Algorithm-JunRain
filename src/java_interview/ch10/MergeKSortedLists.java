package java_interview.ch10;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import java_interview.ch08.ListNode;

public class MergeKSortedLists {
	// 메모리 오류 발생
	// 모든 수를 큐에 넣은 상태로, 반환할 리스트의 뒷부분부터 채워나갈 생각이었다.
	public ListNode mergeKLists(ListNode[] lists) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

		for (ListNode node : lists) {
			while (node.next != null) {
				pq.add(node.val);
			}
		}

		ListNode head = new ListNode(pq.poll());
		while (!pq.isEmpty()) {
			ListNode tail = new ListNode(pq.poll());
			tail.next = head;
			head = tail;
		}

		return head;
	}

	public ListNode mergeKListsPriorityQueue(ListNode[] lists) {
		PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.val == o2.val) {
				return 0;
			} else if (o1.val > o2.val) {
				return 1;
			} else
				return -1;
		});

		ListNode root = new ListNode(0);
		ListNode tail = root;

		// 어차피 각 리스트는 정렬되어 있기 때문에, 각 리스트의 헤드 부분만 우선순위 큐에 삽입
		for (ListNode node : lists) {
			if(node != null) {
				pq.add(node);
			}
		}

		// 큐가 모두 빌 때까지 반복
		while (!pq.isEmpty()) {
			// 우선 순위에 따라 추출, 다음 노드로 이동
			tail.next = pq.poll();
			tail = tail.next;

			// 추출한 연결 리스트의 다음 노드는 다시 큐에 저장
			if (tail.next != null) {
				pq.add(tail.next);
			}
		}

		return root.next;
	}
}
// https://leetcode.com/problems/merge-k-sorted-lists/description/
