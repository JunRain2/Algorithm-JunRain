package java_interview.ch12;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 조합의_합 {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> results = new ArrayList<>();

		dfs(results, candidates, target, 0, 0, new LinkedList<>());

		return results;
	}

	// sum 은 현재의 합
	public void dfs(List<List<Integer>> results, int[] candidates, int target, int sum, int start,
		LinkedList<Integer> element) {
		if (sum == target) {
			results.add(new ArrayList<>(element));
			return;
		}

		// target 보다 현재의 합이 더 클 경우 내려갈 필요가 없기 때문에 리턴
		if (sum > target) {
			return;
		}

		for (int i = start; i < candidates.length; i++) {
			element.add(candidates[i]);
			dfs(results, candidates, target, sum + candidates[i], i, element);
			element.removeLast();
		}
	}
	/**
	 * 내가 푼 풀이
	 * 처음에는 중복을 생각하지 못해서 풀지 못했다.
	 */
	public List<List<Integer>> combinationSumBook(int[] candidates, int target) {
		List<List<Integer>> results = new ArrayList<>();

		dfsBook(results, candidates, target, 0, new ArrayDeque<Integer>());

		return results;
	}

	// sum 은 현재의 합
	public void dfsBook(List<List<Integer>> results, int[] candidates, int target, int index,
		ArrayDeque<Integer> path) {
		if (target == 0) {
			results.add(new ArrayList<>(path));
			return;
		}

		if (target < 0) {
			return;
		}

		for (int i = index; i < candidates.length; i++) {
			path.add(candidates[i]);
			dfsBook(results, candidates, target - candidates[i], i, path);
			path.removeLast();
		}
	}
	/**
	 * target에서 candidate[i]를 빼는 방식으로 불필요한 매개 변수를 줄였다.
	 */
}
// https://leetcode.com/problems/combination-sum/