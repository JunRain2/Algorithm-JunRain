package java_interview.ch10;

import java.util.PriorityQueue;

public class KClosetPoints {

	// 내가 푼 문제
	public int[][] kClosest(int[][] points, int k) {
		// 유클리드에 따라 우선순위 큐 정렬
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
			if (euclid(o1) == euclid(o2)) {
				return 0;
			} else if (euclid(o1) > euclid(o2)) {
				return 1;
			} else {
				return -1;
			}
		});

		for (int[] point : points) {
			pq.add(point);
		}

		int[][] result = new int[k][2];

		for (int i = 0; i < k; i++) {
			result[i] = pq.poll();
		}

		return result;
	}

	// 유클리드를 구해주는 함수
	public double euclid(int[] point) {
		// 개선안 으로 Math.sqrt를 생략할 수 있다.
		// sqrt가 있으나 없으나 우선순위에는 지장이 없기때문
		return Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));
	}
}
// https://leetcode.com/problems/k-closest-points-to-origin/