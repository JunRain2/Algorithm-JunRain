package java_interview.ch11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequent {
	public int[] topKFrequentHashMap(int[] nums, int k) {
		// 각 엘리먼트의 빈도수를 저장할 해시맶 선언, 엘리먼트 => 빈도수
		Map<Integer, Integer> frequencyMap = new HashMap<>();
		// 각엘리먼트의 빈도수를 반복하며 계산하여 저장
		for (int n : nums) {
			frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
		}

		// 빈도수를 기준으로 각 엘리먼트를 저장할 해시맵 선언, 빈도수 => 엘리먼트
		Map<Integer, List<Integer>> buckets = new HashMap<>();
		// 각 엘리먼트를 반복하여 이번에는 빈도수를 키로, 엘리먼트를 값으로 저장
		for (int elem : frequencyMap.keySet()) {
			int frequency = frequencyMap.get(elem);
			// 빈도수에 해당하는 엘리먼트가 존재하지 않으면 빈 리스트 생성
			List<Integer> elems = buckets.getOrDefault(frequency, new ArrayList<>());
			// 저장할 값에 엘리먼트 추가
			elems.add(elem);
			// 빈도수를 키로, 엘리먼트를 값으로 저장
			buckets.put(frequency, elems);
		}

		// 결과로 리턴할 변수 선언, k번까지만 추출하면 되므로 k만큼 선언
		int[] result = new int[k];
		int index = 0;
		// 전체 엘리먼트 수에서 하나씩 내려가며 해당하는 빈도의 엘리먼트 추출
		for (int frequency = nums.length; frequency >= 0 && index < k; frequency--) {
			// 해당 빈도에 값이 존재한다면 엘리먼트를 결과 변수에 삽입
			if (buckets.get(frequency) != null) {
				// 해당 빈도에 여러 엘리먼트가 있을 수 있으므로 반복하여 모두 삽입
				for (int elem : buckets.get(frequency)) {
					result[index] = elem;
					index++;
				}
			}
		}

		return result;
	}

	public int[] topKFrequentPQ(int[] nums, int k) {
		HashMap<Integer, Integer> frequencyMap = new HashMap<>();

		for (int n : nums) {
			frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
		}

		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		for (int elem : frequencyMap.keySet()) {
			pq.add(new int[] {elem, frequencyMap.get(elem)});
		}

		int[] result = new int[k];
		for (int i = 0; i < k; i++) {
			result[i] = pq.poll()[0];
		}

		return result;
	}
}

// https://leetcode.com/problems/top-k-frequent-elements