package java_interview.ch08;

import java.math.BigInteger;

// leetcode 2번
public class AddTwoSumNumbers {
	public ListNode reverseList(ListNode head) {
		ListNode prev = null, node = head;
		while (node != null) {
			ListNode next = node.next;
			node.next = prev;
			prev = node;
			node = next;
		}
		return prev;
	}

	// 연결 리스트를 임의 정밀도 정수형으로 변환
	public BigInteger toBigInt(ListNode node) {
		String val = "";
		while (node != null) {
			val += node.val;
			node = node.next;
		}
		return new BigInteger(val);
	}

	// 임의 정밀도 정수형을 연결 리스트로 변환
	public ListNode toReversedLinkedList(BigInteger val) {
		ListNode prev = null, node = null;
		for (char c : String.valueOf(val).toCharArray()) {
			// 한 글자씩 숫자로 변환하여 노드 지정
			// 숫자 형태의 char형을 int형으로 변환
			node = new ListNode(Character.getNumericValue(c));
			node.next = prev;
			prev = node;
		}
		return node;
	}

	// 우아한 방식이 아닌 난잡한 방식
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		// 두 리스트를 뒤집는다.
		ListNode l1Reversed = reverseList(l1);
		ListNode l2Reversed = reverseList(l2);

		// 두 리스트를 임의 정밀도 정수형으로 변환하여 더하기 연산을 진행한다.
		BigInteger result = toBigInt(l1Reversed).add(toBigInt(l2Reversed));
		// 결과를 다시 역순 연결 리스트로 변환한다.
		return toReversedLinkedList(result);
	}

	// 전가산기 구현

	/**
	 * 자리 올림수(carry)를 구하는 것까지 가산기의 원리와 거의 동일
	 * 덧셈 결과에 나머지를 취하고 몫은 자리올림수 형태로 올리는 전가산기의 전체적인 구조만 참고
	 */
	public ListNode addTwoNumberFullAdd(ListNode l1, ListNode l2) {
		// 값을 계산할 임시 노드
		ListNode node = new ListNode(0);
		// 임시 노드를 첫 번째 노드로 선언
		ListNode root = node;

		// 자릿수의 합 (sum), 자리올림수(carry), 나머지(remainder)를 저장할 변수 선언
		int sum, carry = 0, remainder;
		// 모든 연결 리스트를 끝까지 순회하고, 자리올림수도 0이 될 때까지 진행
		while (l1 != null || l2 != null || carry != 0) {
			sum = 0;
			if (l1 != null) {
				sum += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				sum += l2.val;
				l2 = l2.next;
			}

			// 노드의 값으로 사용할 나머지 계산
			remainder = (sum + carry) % 10;
			// 10으로 나누었을 때 몫은 자릿수가 증가했다는 의미이므로 자리올림수로 사용 -> 즉 다음 loop때 사용 할 carry
			carry = (sum + carry) / 10;
			// 나머지는 다음 노드의 값으로 지정
			node.next = new ListNode(remainder);
			// 계산할 노드를 다음으로 이동
			node = node.next;
		}

		// 첫 번째 노드는 임시 노드이므로 그다음부터 결과로 리턴
		return root.next;
	}
}
