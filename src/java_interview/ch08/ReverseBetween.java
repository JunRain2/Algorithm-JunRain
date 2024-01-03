package java_interview.ch08;

public class ReverseBetween {
	// 벽이 느껴지는 셔플 방법
	// start와 end 라는 두 개의 포인터로 left와 right를 역순으로 정렬
	// 어떻게 이런 아이디어를 도출해 낼 수 있었을까?
	public ListNode reverseBetween(ListNode head, int left, int right) {
		if (head == null) {
			return null;
		}
		ListNode root = new ListNode(0);
		root.next = head;

		ListNode start = root;
		for (int i = 0; i < left - 1; i++) {
			start = start.next;
		}

		ListNode end = start.next;
		// end가 계속해서 뒤로 밀려나기 때문에 진행될 수 있다.
		for (int i = 0; i < right - left; i++) {
			ListNode tmp = start.next;
			start.next = end.next;
			end.next = end.next.next;
			start.next.next = tmp;
		}

		return root.next;
	}
}
// https://leetcode.com/problems/reverse-linked-list-ii