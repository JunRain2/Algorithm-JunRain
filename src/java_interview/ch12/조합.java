package java_interview.ch12;

import java.util.ArrayList;
import java.util.List;

public class 조합 {
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> result = new ArrayList<>();

		dfs(n, k, 0, result, new ArrayList<>());

		return result;
	}

	// index는 현재 숫자
	public void dfs(int n, int k, int index, List<List<Integer>> result, List<Integer> list) {
		// list.size()가 k와 같으면 result에 추가하가 return
		if (list.size() == k) {
			result.add(list);
			return;
		}

		// index+1 부터 시작해서 n 까지
		// index 부터 시작하면 [1,1,1] 과 같은 결과가 추가될 수 있음.
		for (int i = index + 1; i <= n; i++) {
			// 기존 리스트에 추가할 경우, 리스트가 오염되어 예상치 못하는 값이 나올 수 있음
			// 따라서 새로운 리스트에 추가.
			List<Integer> tmp = new ArrayList<>(list);
			tmp.add(i);
			dfs(n, k, i, result, tmp);
		}
	}
	/**
	 * 느리긴 하지만 작동을 한다...
	 * 결국에는 이전, 순열과 같은 매커니즘으로 작동
	 */
}
// https://leetcode.com/problems/combinations/description/