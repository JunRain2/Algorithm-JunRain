package java_interview.ch15;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class 배열의_K번째_큰_엘리먼트 {
	public int findKthLargest(int[] nums, int k) {
		Queue<Integer> minHeap = new PriorityQueue<>(Collections.reverseOrder());

		for (int num : nums) {
			minHeap.offer(num);
		}

		int result = 0;

		for (int i = 0; i < k; i++) {
			result = minHeap.poll();
		}

		return result;
	}
}
