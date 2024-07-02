package java_interview.ch18;

import java.util.Arrays;

public class 두_수의_합_2 {
	public int[] twoSum(int[] numbers, int target) {
		int[] result = new int[2];

		for (int i = 0; i < numbers.length; i++) {
			int tmp = target - numbers[i];

			int index = Arrays.binarySearch(numbers, tmp);
			if (index >= 0) {
				if (index == i) {
					continue;
				}
				result[0] = i + 1;
				result[1] = index + 1;
				break;
			}
		}

		Arrays.sort(result);

		return result;
	}
}
