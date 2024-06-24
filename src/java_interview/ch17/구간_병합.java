package java_interview.ch17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 구간_병합 {
	public int[][] merge(int[][] intervals) {
		Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

		List<int[]> result = new ArrayList<>();
		result.add(intervals[0]);

		int resultIndex = 0;

		for (int i = 1; i < intervals.length; i++) {
			if (result.get(resultIndex)[1] >= intervals[i][0]) {
				result.set(resultIndex, new int[] {
					result.get(resultIndex)[0], Math.max(result.get(resultIndex)[1], intervals[i][1])
				});
			} else {
				result.add(intervals[i]);
				resultIndex++;
			}
		}

		return result.toArray(new int[result.size()][]);
	}
}
