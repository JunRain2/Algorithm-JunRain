package java_interview.ch07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 브루트 포스 방식으로 문제를 풀 경우 O(n^3) 으로 문제가 풀리지 않는다.
 */
public class ThreeSum {
	/***
	 * 투 포인터를 이용한 방식으로 O(n^2)
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		int left, right, sum;
		List<List<Integer>> results = new ArrayList<>();
		Arrays.sort(nums);

		for (int i = 0; i < nums.length - 2; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;

			left = i + 1;
			right = nums.length - 1;
			// 두 포인터가 만나기 전까지
			while (left < right) {
				sum = nums[i] + nums[left] + nums[right];
				// 오름차순 -> 0에 가깝게 하기 위해 left 포인트를 1 증가
				if (sum < 0) {
					left += 1;
					// 0에 가깝기 하기 위해 right 포인트를 1 증가
				} else if (sum > 0) {
					right -= 1;
				} else {
					results.add(Arrays.asList(nums[i], nums[left], nums[right]));

					// 값의 중복을 막기 위해 이전 값과 동일하면 포인터를 이동
					while (left < right && nums[left] == nums[left + 1])
						left += 1;
					while (left < right && nums[right] == nums[right - 1])
						right -= 1;

					left += 1;
					right -= 1;
				}
			}
		}
		return results;
	}
}
// https://leetcode.com/problems/3sum