package java_interview.ch07;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

	// 2번째 해설의 2개의 for문을 하나의 for문으로 결합
	public static int[] twoSumThird(int[] nums, int target) {
		Map<Integer, Integer> numsMap = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			if (numsMap.containsKey(target - nums[i])) {
				return new int[] {numsMap.get(target - nums[i]), i};
			}
			numsMap.put(nums[i], i);
		}
		return null;
	}

	public static void main(String[] args) {
		int[] nums = new int[] {2, 7, 11, 15};
		twoSumThird(nums, 9);
	}

	// 배열을 모두 탐색해야 해서 비효율적
	public int[] twoSumFirst(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[] {i, j};
				}
			}
		}
		return null;
	}

	public int[] twoSumSecond(int[] nums, int target) {
		Map<Integer, Integer> numsMap = new HashMap<>();
		// key -> nums의 value, value -> nums의 인덱스
		for (int i = 0; i < nums.length; i++) {
			numsMap.put(nums[i], i);
		}

		// target에서 첫 번째 수를 뺀 결과를 키로 조회하고 현재 인덱스가 아닌 경우 정답으로 리턴
		// 인덱스가 중복될 경우를 방지
		for (int i = 0; i < nums.length; i++) {
			if (numsMap.containsKey(target - nums[i]) && i != numsMap.get(target - nums[i])) {
				return new int[] {i, numsMap.get(target - nums[i])};
			}
		}
		return null;
	}

	// 투포인터를 이용한 문제 풀이
	// 왼쪽 포인터와 오른쪽 포인터의 합이 타깃보다 크다면 오른쪽 포인터를 왼쪽으로, 작다면 왼쪽 포인터를 오른쪽으로 옮기면서 값을 조정
	/**
	 * 투 포인터로 풀 수 없는 문제
	 * 투 포인터는 주로 정렬된 입력값을 대상으로 하며, nums는 정렬된 상태가 아니기 때문
	 * 만약 nums를 정렬하게 되면 인덱스가 꼬이게 되어 심각한 문제가 발생
	 */
	public int[] twoSumFourth(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;

		while (left != right) {
			if (nums[left] + nums[right] < target) {
				left += 1;
			} else if (nums[left] + nums[right] > target) {
				right -= 1;
			} else {
				return new int[] {left, right};
			}
		}
		return null;
	}
}
