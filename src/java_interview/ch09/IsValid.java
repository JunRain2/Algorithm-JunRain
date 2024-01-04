package java_interview.ch09;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class IsValid {
	/**
	 * 직접 짠 코드
	 * 난잡하다
	 * 처음에는 아스키 코드를 이용하려 했지만, ( 의 + 1은 ) 이지만 다른 괄호는 +2 씩 해줘야 해서 패스함.
	 */
	public boolean isValidSelf(String s) {
		Deque<Character> stack = new ArrayDeque<>();
		char[] chars = s.toCharArray();

		for (char c : chars) {
			if (c == '[' || c == '{' || c == '(') {
				stack.push(c);
			} else {
				// "]"와 같이 한단어가 올 경우
				if (stack.isEmpty()) {
					return false;
				}
				char tmp = stack.pop();
				if (tmp == '[') {
					if (c != ']') {
						return false;
					}
				} else if (tmp == '{') {
					if (c != '}') {
						return false;
					}
				} else if (tmp == '(') {
					if (c != ')') {
						return false;
					}
				}

			}
		}
		// "[" 와 같이 한 단어가 올 경우
		if (!stack.isEmpty()) {
			return false;
		}

		return true;
	}

	/**
	 * 괄호들을 Map 에 저장하면서 훨씬 우아한 코드가 완성 되었다.
	 * 괄호는 짝이 있기 떄문에 Map 을 활용
	 */
	public boolean isValidStack(String s) {
		Deque<Character> stack = new ArrayDeque<>();
		Map<Character, Character> table = new HashMap<>() {{
			put(')', '(');
			put('}', '{');
			put(']', '[');
		}};

		for (int i = 0; i < s.length(); i++) {
			// 열리는 괄호면 stack에 push
			if (!table.containsKey(s.charAt(i))) {
				stack.push(s.charAt(i));
				// 닫히는 괄호를 key로 여는 괄호값을 가져와 stack 에 있는 여는 괄호와 일치하는지 확인
			} else if (stack.isEmpty() || table.get(s.charAt(i)) != stack.pop()) {
				return false;
			}
		}

		// 유효한 입력이 되려면 반복 완료 후 스택이 비어야 한다.
		return stack.size() == 0;
	}
}
// https://leetcode.com/problems/valid-parentheses/