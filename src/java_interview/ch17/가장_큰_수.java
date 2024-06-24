package java_interview.ch17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 가장_큰_수 {
	public String largestNumber(int[] nums) {
		boolean zero = true;
		List<String> numbers = new ArrayList<>();

		for (int n : nums) {
			if(n != 0) zero = false;
			numbers.add(String.valueOf(n));
		}

		if(zero) return "0";

		Collections.sort(numbers, this::compareString);

		StringBuilder sb = new StringBuilder();
		for (String s : numbers) {
			sb.append(s);
		}

		return sb.toString();
	}

	public int compareString(String o1, String o2) {
		String o1Tmp = new StringBuilder(o1).append(o2).toString();
		String o2Tmp = new StringBuilder(o2).append(o1).toString();

		return o2Tmp.compareTo(o1Tmp);
	}
}
