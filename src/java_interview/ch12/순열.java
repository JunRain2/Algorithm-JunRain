package java_interview.ch12;

import java.util.ArrayList;
import java.util.List;

public class 순열 {
	public List<List<Integer>> permute(int[] nums) {
		List<Integer> numList = new ArrayList<>();
		for (int n : nums) {
			numList.add(n);
		}

		List<List<Integer>> result = new ArrayList<>();

		dfs(result, numList, new ArrayList<>());

		return result;
	}

	public void dfs(List<List<Integer>> result, List<Integer> subList, List<Integer> path) {
		if (subList.size() == 0) {
			result.add(path);
			return;
		}

		for (int i = 0; i < subList.size(); i++) {
			List<Integer> pathTmp = new ArrayList<Integer>(path);
			List<Integer> subListTmp = new ArrayList<Integer>(subList);
			pathTmp.add(subList.get(i));
			subListTmp.remove(i);
			dfs(result, subListTmp, pathTmp);
		}
	}
	/**
	 * 전화번호 문자 조합에서 사용했던 컨셉을 사용하여 문제를 풀었다.
	 */
}
// https://leetcode.com/problems/permutations/description/