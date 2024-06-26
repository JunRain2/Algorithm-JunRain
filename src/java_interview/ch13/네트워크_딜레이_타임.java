package java_interview.ch13;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * dist에 값이 없는 경우
 * 1. pq 에서 거리가 제일 작은 노드A를 꺼낸다.
 * 2. 해당 노드A를 dist에 넣는다.
 * 3. 출발지가 노드 A인 값을 graph에서 찾는다.
 * 4. 출발지에서 노드 A까지의 거리를 graph에서 찾은 노드의 거리에 합한 후 pq에 넣는다. -> 출발지를 기준으로 제일 작은 거리를 재는 것이기 때문
 * 5. 1. 부터 반복한다.
 *
 * dist에 값이 있는 경우
 *    dist에 있는 노드의 거리가 가장 짧기 때문에 그냥 넘어간다.
 */
public class 네트워크_딜레이_타임 {
	/**
	 * 2가지 사항을 판별
	 * 1. 모든 노드가 신호를 받는 데 걸리는 시간 -> 가장 오래 걸리는 노드까지의 최단 시간, 다익스트라 알고리즘으로 계산
	 * 2. 모든 노드에 도달할 수 있는지 여부 -> 모든 노드의 다익스트라 알고리즘 계산값이 존재하는지 유무로 파악 가능
	 * 		만약 노드가 8개인데 다익스트라 알고리즘 계산은 7개밖에 할 수 없다면, 나머지 한 노드에는 도달할 수 없다는 의미
	 */
	public int networkDelayTime(int[][] times, int n, int k) {
		// key(출발지) : value(key(도착지) : value(비용))
		Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
		for (int[] time : times) {
			// 값이 존재하지 않을 경우 빈 해시맵 생성
			graph.putIfAbsent(time[0], new HashMap<>());
			// 출발지에 (도착지, 소요 시간) 추가
			graph.get(time[0]).put(time[1], time[2]);
		}
		// 우선순위 큐 생성, 값이 graph의 value(도착지, 소요 시간)으로 구성되므로
		// 정렬 기준으로 도착지(key)와 소요 시간(value) 중 소요 시간을 기준으로 한다. -> 짧은 경로를 계산하는 것이기 때문
		// Map이 아닌 Map.Entry를 사용 한 이유
		// -> 맵은 기본적으로 목록형(List)이기 때문에 값을 조회하려면 항상 for 문으로 순회하여 추출하는 번거로운 과정이 필요
		// -> Map.Entry는 항상 1개의 아이템을 의미하므로 getKey(), getValue() 메소드로 순회 과정 없이 즉시 값을 추출할 수 있다.
		Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());
		// (도착지, 소요 시간)을 큐에 삽입, 시작은 출발지 k이며, 소요 시간은 출발지이므로 당연히 0이다.
		pq.add(new AbstractMap.SimpleEntry<>(k, 0));

		// (도착지, 소요 시간) 최종 결과를 저장하는 변수 선언
		Map<Integer, Integer> dist = new HashMap<>();
		// 큐에 값이 남아 있다면 없어질 때까지 반복
		// pq를 기준으로 탐색하기 때문에, 방문했던 노드를 기준으로 최소 거리를 찾아갈 수 있음.
		// 메인 로직
		while (!pq.isEmpty()) {
			// 소요 시간이 가장 빠른 값 추출 -> value가 가장 작은 값부터 추출
			Map.Entry<Integer, Integer> cur = pq.poll();
			// 우선순위 큐의 항복이 Map.Entry이므로 순회 과정 없이
			// 간단히 getKey(), getValue()로 (도착지, 소요 시간)을 조회할 수 있다.
			int u = cur.getKey();
			int dist_u = cur.getValue();

			// u 지점까지의 소요 시간이 아직 계산되지 않았다면 처리 시작
			if (!dist.containsKey(u)) {
				// u 지점까지의 소요 시간(dist_u)을 결과 변수에 삽입
				dist.put(u, dist_u);
				// u 지점을 출발지로 한 경로가 있다면 처리 시작
				if (graph.containsKey(u)) {
					// u 지점을 출발지로 한 모든 경로 순회, v는 u의 도착지들
					// pq의 영역을 넓히는 부분 -> 즉 dist에 넣은 도착지를 출발지로 하여 pq에 저장하는 과정
					for (Map.Entry<Integer, Integer> v : graph.get(u).entrySet()) {
						// u 지점까지의 소요 시간(dist_u) + u 지점을 출발지로 한 도착까지의 소요 시간
						// 출발지로부터 최단거리만 골라서 도착한 소요 시간
						int alt = dist_u + v.getValue();
						// 우선순위 큐에 (도착지, 소요 시간)을 삽입
						pq.add(new AbstractMap.SimpleEntry<>(v.getKey(), alt));
					}
				}
			}
		}

		// 결과 변수가 노드의 수와 같다면 모든 노드의 소요 시간을 측정했고, 도달 가능하다는 의미
		if (dist.size() == n) {
			// 이 중 가장 오래 걸리는 지점의 소요 시간 추출
			return Collections.max(dist.values());
		}
		// 같지 않다면 일부 노드에는 도달할 수 없으므로 문제 조건에 따라 -1 리턴
		return -1;
	}
}
// https://leetcode.com/problems/network-delay-time
