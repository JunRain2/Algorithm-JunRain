package java_interview.ch09;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class DailyTemperatures {
	/**
	 * 스택에 값을 넣으면 안풀리던 문제가 스택에 index를 넣자마자 바로 풀림
	 * 좀 더 그림을 더 넓게 그려야 함
	 * 값으로만 풀려고 했더니 안풀리는거였네, 스택에 index를 넣을 생각을 진짜 1도 안했네 반성하자
	 */
	public int[] dailyTemperatures(int[] temperatures) {
		int[] result = new int[temperatures.length];
		Arrays.fill(result, 0);

		Deque<Integer> stack = new ArrayDeque<>();

		for (int i = 0; i < temperatures.length; i++) {
			while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
				int tmp = stack.pop();
				result[tmp] = i - tmp;
			}
			stack.push(i);
		}

		return result;
	}
}
// https://leetcode.com/problems/daily-temperatures/