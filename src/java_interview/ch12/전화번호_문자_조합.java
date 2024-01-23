package java_interview.ch12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 전화번호_문자_조합 {
	public List<String> letterCombinations(String digits) {
		List<String> result = new ArrayList<>();

		// 예외 처리
		if (digits.length() == 0) {
			return result;
		}

		Map<Character, List<Character>> dic = new HashMap<>(
			Map.ofEntries(
				Map.entry('2', new ArrayList<>(Arrays.asList('a', 'b', 'c'))),
				Map.entry('3', new ArrayList<>(Arrays.asList('d', 'e', 'f'))),
				Map.entry('4', new ArrayList<>(Arrays.asList('g', 'h', 'i'))),
				Map.entry('5', new ArrayList<>(Arrays.asList('j', 'k', 'l'))),
				Map.entry('6', new ArrayList<>(Arrays.asList('m', 'n', 'o'))),
				Map.entry('7', new ArrayList<>(Arrays.asList('p', 'q', 'r', 's'))),
				Map.entry('8', new ArrayList<>(Arrays.asList('t', 'u', 'v'))),
				Map.entry('9', new ArrayList<>(Arrays.asList('w', 'x', 'y', 'z')))
			)
		);

		// 현재 자리 0, 빈 문자열 이용 DFS 시작
		dfs(result, dic, digits, 0, new StringBuilder());
		return result;
	}

	public void dfs(List<String> result, Map<Character, List<Character>> dic, String digits, int index,
		StringBuilder path) {
		// 끝까지 탐색했으면 결과를 저장하고 리턴
		if (path.length() == digits.length()) {
			result.add(String.valueOf(path));
			return;
		}

		// 현재 자리 숫자에 해당하는 모든 문자열 탐색
		for (Character c : dic.get(digits.charAt(index))) {
			// 현재 자리 + 1, 지금까지 구성된 문자열 path 이용 재귀 DFS
			// 최종 결과는 문자열이 저장된 path가 되며, 결과로 처리한 이휴에는 path의 내용이 유지되면 안된다.
			// path는 내용이 유지되면 안되기 때문에 new StringBuilder(path)를 통해 새로운 인스턴스를 생성해 넘겨준다.
			dfs(result, dic, digits, index + 1, new StringBuilder(path).append(c));
		}
	}

	/**
	 * 생각한 풀이 방식과 얼추 일치, 아직 재귀에 대한 접근 방법을 잘 몰라서 문제를 못푸는 것 같다.
	 * 재귀를 예측하는 능력을 키워야할 것 같다. -> 문제를 많이 풀어보면서 계속 생각해야 함.
	 */
}
// https://leetcode.com/problems/letter-combinations-of-a-phone-number/