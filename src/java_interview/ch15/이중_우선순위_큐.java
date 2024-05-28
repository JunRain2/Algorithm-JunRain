package java_interview.ch15;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class 이중_우선순위_큐 {
	public int[] solution(String[] operations) {
		Queue<Integer> minHeap = new PriorityQueue<>();
		Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

		int size = 0;

		for (String operation : operations) {
			String[] s = operation.split(" ");

			if (s[0].equals("I")) {
				minHeap.add(Integer.parseInt(s[1]));
				maxHeap.add(Integer.parseInt(s[1]));
				size++;
			}

			if (size > 0) {
				if (s[0].equals("D")) {
					if (s[1].equals("-1")) {
						minHeap.poll();
						size--;

					}
					if (s[1].equals("1")) {
						maxHeap.poll();
						size--;

					}
				}
			}
			if (size == 0) {
				minHeap.clear();
				maxHeap.clear();
			}
		}

		if (size > 0) {
			return new int[] {maxHeap.poll(), minHeap.poll()};
		}

		return new int[] {0, 0};
	}
}

