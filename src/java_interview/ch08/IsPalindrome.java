package java_interview.ch08;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class IsPalindrome {
	// 리스트의 맨 뒤에서 부터와 맨 앞에서 부터 비교했을 때 같으면 true, 틀리면 false
	public boolean isPalindromeStack(ListNode head) {
		Deque<Integer> stack = new ArrayDeque<>();
		ListNode node = head;
		while (node != null) {
			stack.push(node.val);
			node = node.next;
		}

		while (head != null) {
			if (head.val != stack.pop()) {
				return false;
			}
			head = head.next;
		}
		return true;
	}

	public boolean isPalindromeDeque(ListNode head) {
		// 양방향 큐
		Deque<Integer> deque = new LinkedList<>();
		ListNode node = head;
		while (node != null) {
			deque.offer(node.val);
			node = node.next;
		}

		// deque의 양 끝을 비교함.
		// deque.size > 1 -> 리스트가 홀수일 경우
		while (!deque.isEmpty() && deque.size() > 1) {
			if (deque.pollFirst() != deque.pollLast()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 빠른 러너와 느린 러너를 각각 출발 시킴
	 * 빠른 러너가 끝에 다다를 때 느린 러너는 정확히 중간 지점에 도달
	 * 느린 러나가 중간까지 이동한 후에는 나머지 경로를 역순으로 하여 연결 리스트 rev를 만들어 나간다.
	 * rev를 만들고 난 후에 원래 연결리스트와 일치하는지 확인
	 *
	 * 홀수 일 때, 느린 러너가 한 칸 더 앞으로 이동해 중앙의 값을 빗겨나가야 한다. -> 중앙값을 팰린드롬 체크에서 배제
	 */
	public boolean isPalindromeRunner(ListNode head) {
		ListNode fast = head, slow = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}

		// 홀수 개 일 때
		if (fast != null) {
			slow = slow.next;
		}

		// slow 이후의 head 리스트의 역순 리스트를 만드는 과정
		ListNode rev = null;
		while (slow != null) {
			ListNode next = slow.next;
			slow.next = rev;
			rev = slow;
			// slow는 slow.next 즉 head 리스트의 다음으로 향함
			slow = next;
		}

		while (rev != null) {
			if (rev.val != head.val) {
				return false;
			}
			rev = rev.next;
			head = head.next;
		}

		return true;
	}

	class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}
}
