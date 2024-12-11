package java_interview.ch22;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class 과반수_엘리먼트 {
	public int majorityElement(int[] nums) {
		Map<Integer, Integer> result = new HashMap<>();

		for (int num : nums) {
			int cnt = result.getOrDefault(num, 0);
			result.put(num, ++cnt);
		}

		return Collections.max(result.entrySet(), Map.Entry.comparingByValue()).getKey();
	}
}
