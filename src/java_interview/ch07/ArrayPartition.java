package java_interview.ch07;

import java.util.Arrays;

/**
 * n개의 페어를 이용한 min(a, b)의 합으로 만들 수 있는 가장 큰 수를 출력 -> 오름차순으로 정렬한 후 두 개의 값을 페어로 묶음
 */
public class ArrayPartition {
	public int arrayPairSum(int[] nums) {
		int result = 0;

		Arrays.sort(nums);

		for (int i = 0; i < nums.length; i += 2) {
			result += nums[i];
		}

		return result;
	}
}
// https://leetcode.com/problems/array-partition