package java_interview.ch10;

import java.util.PriorityQueue;

public class 더_맵게 {
	// 내가 푼 문제
	public int solution(int[] scoville, int K) {
		int answer = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i : scoville) {
			pq.offer(i);
		}

		// 우선순위 큐의 맨 앞이 K 보다 크면 큐 안의 모든 숫자가 스코빌 지수를 만족
		while (pq.peek() < K) {
			// 스코필 지수를 만들지 못할 때 -1을 반환
			if (pq.size() < 2) {
				return -1;
			}
			pq.offer(pq.poll() + pq.poll() * 2);
			answer++;
		}

		return answer;
	}
}
// https://school.programmers.co.kr/learn/courses/30/lessons/42626?language=java