package java_interview.ch22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class 괄호를_삽입하는_여러_가지_방법 {
	HashMap<String, List<Integer>> memo = new HashMap<>();

	public List<Integer> diffWaysToCompute(String expression) {
		List<Integer> result = new ArrayList<>();
		// 이미 계산해둔 동일한 표현식이라면 기존 결과를 재활용
		if (memo.containsKey(expression)) {
			return memo.get(expression);
		}

		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			// 연산자가 등장하면 좌, 우 분할하여 각각 계산 결과를 리턴받아 처리한다.
			if (c == '+' || c == '-' || c == '*') {
				// 연산자가 등장한 위치를 기준으로 좌, 우 분할
				List<Integer> left = diffWaysToCompute(expression.substring(0, i));
				List<Integer> right = diffWaysToCompute(expression.substring(i + 1));

				// 좌, 우 분할된 표현식의 연산 결과 리스트를 받아서 모두 순회하며 연산 처리
				for (int l : left) {
					for (int r : right) {
						if (c == '+') {
							result.add(l + r);
						} else if (c == '-') {
							result.add(l - r);
						} else if (c == '*') {
							result.add(l * r);
						}
					}
				}
			}
		}
		// 연산 결과가 없다는 것은 연산자가 포함되어 있지 않다는 뜻이며, 청숫값만 있는 경우이므로 이 값만 담아서 리턴
		if (result.isEmpty()) {
			result.add(Integer.parseInt(expression));
		}
		// 계산 결과는 추후 재활용을 위해 저장
		memo.put(expression, result);

		return result;
	}
}
