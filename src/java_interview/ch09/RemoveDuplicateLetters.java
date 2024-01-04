package java_interview.ch09;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class RemoveDuplicateLetters {
	public static Set<Character> toStortedSet(String s) {
		// 문자열을 문자 단위 집합으로 저장할 변수 선언, TreeSet -> 값에 따라 순서가 결정된다.
		Set<Character> set = new TreeSet<>(new Comparator<Character>() {
			@Override
			public int compare(Character o1, Character o2) {
				// 동일 문자면 무시 -> 저장하지 않는다.
				if (o1 == o2) {
					return 0;
				}
				// 새로운 문자(o1)가 기본 문자(o2)보다 크면 뒤에 위치
				else if (o1 > o2) {
					return 1;
				}
				// 작으면 앞에 위치
				else
					return -1;

			}
		});
		// 문자열을 문자 단위로 집합에 저장, 정렬된 상태로 저장된다.
		for (int i = 0; i < s.length(); i++) {
			set.add(s.charAt(i));
		}
		return set;
	}

	/**
	 * 하나만 있는 문자는 위치가 고정된다
	 * s = "db" 일 경우
	 * 정렬된 set은 "bd" 가 된다. 따라서 첫번째 루프에서 'b'가 선택되어서,
	 * suffix = "b" 가 되면서 전체 집합과 접미사 집합이 일치하지 않게 된다.
	 * 따라서 다음 루프로 넘어가고 모든 조건이 일치하면서 먼저 'd'가 리턴되고 'b'가 리턴되게 된다.
	 */
	public static String removeDuplicateLettersRecursive(String s) {
		Set<Character> sortedSet = toStortedSet(s);
		// 정렬된 문자열 집합 순회
		for (char c : sortedSet) {
			// 해당 문자가 포함된 위치부터 접미사로 지정
			String suffix = s.substring(s.indexOf(c));
			// 전체 집합과 접미사 집합이 일치하면 해당 문자 정답에 추가하고 해당 문자를 지운 접미사를 매개변수로 재귀 호출 진행
			if (sortedSet.equals(toStortedSet(suffix))) {
				return c + removeDuplicateLettersRecursive(suffix.replace(String.valueOf((c)), ""));
			}
		}
		return "";
	}

	public static String removeDuplicateLettersStack(String s) {
		// 문자 개수를 계산해서 담아둘 변수 선언
		Map<Character, Integer> counter = new HashMap<>();
		// 이미 처리한 문자 여부를 확인하기 위한 변수 선언
		Map<Character, Boolean> seen = new HashMap<>();
		// 문제 풀이에 사용할 스택 선언
		Deque<Character> stack = new ArrayDeque<>();

		// 문자별 개수 계산
		for (char c : s.toCharArray()) {
			counter.put(c, counter.get(c) == null ? 1 : counter.get(c) + 1);
		}

		for (char c : s.toCharArray()) {
			// 현재 처리하는 문자는 카운터에서 -1 처리
			counter.put(c, counter.get(c) - 1);
			// 이미 처리한 문자는 건너 뛴다.
			if (seen.get(c) != null && seen.get(c) == true) {
				continue;
			}
			// 스택에 있는 문자가 현재 문자보다 더 뒤에 붙어야 할 문자라면 스택에서 제거하고
			// 처리하지 않은 걸로 표시
			while (!stack.isEmpty() && stack.peek() > c && counter.get(stack.peek()) > 0) {
				seen.put(stack.pop(), false);
			}
			//현재 문자를 스택에 삽입
			stack.push(c);
			// 현재 문자를 처리한 걸로 선언
			seen.put(c, true);
		}
		// 스택에 있는 문자를 큐 순서대로 추출하여 리턴
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pollLast());
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		removeDuplicateLettersRecursive("dbacdcbc");
	}

	// 문제를 잘못 이해하고 있었다.
	// "cbacdcbc" ->"acdb" 한번 나온 b의 위치는 변경할 수 없기 떄문에 d 다음에 b가 온다.
	// 나의 풀이는 그냥 중복된 문자를 제거한 후 문자들을 사전순으로 재배치한 것
	public String removeDuplicateLetters(String s) {
		Set<Character> set = new LinkedHashSet<>();

		for (int i = 0; i < s.length(); i++) {
			set.add(s.charAt(i));
		}

		List<Character> chars = new LinkedList<>(set);
		Collections.sort(chars);
		StringBuilder result = new StringBuilder();
		for (char c : chars) {
			result.append(c);
		}
		return result.toString();
	}

}
// https://leetcode.com/problems/remove-duplicate-letters/
