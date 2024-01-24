package java_interview.ch12;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

	public List<List<Integer>> combineBook(int n, int k) {
		List<List<Integer>> results = new ArrayList<>();

		dfsBook(results, new LinkedList<>(), n, 1, k);

		return results;
	}

	public void dfsBook(List<List<Integer>> result, LinkedList<Integer> element, int n, int start, int k) {
		// k번째 노드에 도달하면 결과에 추가
		if (k == 0) {
			// 자바 8+ 스트림을 이용해 elements의 내용을 결과에 삽입 -> 엄청 느림
			// result.add(element.stream().collect(Collectors.toList()));
			// 깊은 복사를 사용하는 이 방법이 더 빠르다.
			result.add(new ArrayList<>(element));
			return;
		}

		// 현재 엘리먼트 이후 엘리먼트 검색
		for (int i = start; i <= n; i++) {
			// 전달받은 엘리먼트에 더해 현재 엘리먼트 추가
			element.add(i);
			// 재귀 DFS
			dfsBook(result, element, n, i + 1, k - 1);
			// 돌아온 이후에는 현재 엘리먼트 삭제
			element.removeLast();
		}
	}

	/**
	 * dfs에서 원소를 넣었다 뺌으로써 리스트를 하나 더 만들 필요없이 하나의 리스트를 재활용할 수 있다.
	 * 스트림을 이용해 새로운 리스트를 만들어 추가.
	 */
}
// https://leetcode.com/problems/combinations/description/