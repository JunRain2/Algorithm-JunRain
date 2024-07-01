package java_interview.ch18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 두_배열의_교집합 {
	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> nSet1 = new HashSet<>();
		Set<Integer> nSet2 = new HashSet<>();

		for (int n : nums1) {
			nSet1.add(n);
		}
		for (int n : nums2) {
			nSet2.add(n);
		}

		List<Integer> result = new ArrayList();

		for (int n : nSet1) {
			if (nSet2.contains(n)) {
				result.add(n);
			}
		}

		return result.stream().mapToInt(Integer::intValue).toArray();
	}
}
