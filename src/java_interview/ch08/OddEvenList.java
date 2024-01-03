package java_interview.ch08;

public class OddEvenList {

	//내가 푼 풀이
	public ListNode oddEvenList(ListNode head) {
		// 홀수번째 노드들
		ListNode odd = new ListNode(0);
		// 짝수번째 노드들
		ListNode even = new ListNode(0);
		ListNode rootOdd = odd;
		ListNode rootEven = even;
		// 홀수인지 짝수인지 구별하기 위한 count
		int count = 1;

		while (head != null) {
			// 짝수일때, even 연결 리스트에 연결
			if (count % 2 == 0) {
				even.next = head;
				even = even.next;
				// 홀수일때, odd 연결 리스트에 연결
			} else if (count % 2 == 1) {
				odd.next = head;
				odd = odd.next;
			}
			head = head.next;
			count++;
		}

		// even 마지막 노드의 next에 null을 지정해서 연결 리스트에 싸이클이 생기는 것을 방지
		// head 에서 값을 대입할 때 연결부분 또한 가져오기 때문
		even.next = null;
		odd.next = rootEven.next;
		return rootOdd.next;
	}

	// 책 풀이
	// 굉장히 우아한 풀이
	// 지향해야 할 풀이
	public ListNode oddEvenListLoop(ListNode head) {
		if (head == null) {
			return null;
		}
		// 홀수 노드
		ListNode odd = head;
		// 짝수 노드
		ListNode even = head.next;
		// 짝수 첫 번째 노드
		ListNode evenHead = even;

		// 반복하면서 홀짝 노드 처리
		while (even != null && even.next != null) {
			odd.next = odd.next.next;
			even.next = even.next.next;
			odd = odd.next;
			even = even.next;
		}
		odd.next = evenHead;
		return head;
	}
}
