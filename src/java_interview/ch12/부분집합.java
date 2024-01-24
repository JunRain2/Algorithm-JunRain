package java_interview.ch12;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 부분집합 {
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> results = new ArrayList<>();

		dfs(results, nums, 0, new LinkedList<>());

		return results;
	}

	public void dfs(List<List<Integer>> results, int[] nums, int index, LinkedList<Integer> elements) {
		results.add(new ArrayList<>(elements));

		// 현재 인덱스의 위치와 배열의 길이가 일치하면 return
		// 제한을 걸어두지 않으면 overflow 예외 발생
		if (index == nums.length) {
			return;
		}

		for (int i = index; i < nums.length; i++) {
			elements.add(nums[i]);
			dfs(results, nums, i + 1, elements);
			elements.removeLast();
		}
	}
	/**
	 * 재귀를 실행할 때, 추가된 list를 넘겨주기 때문에
	 * dfs를 시작할 때 results에 elements을 먼저 추가해 줘야함
	 */
}
// https://leetcode.com/problems/subsets/