package java_interview.ch13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class k_경유지_내_가장_저렴한_항공권 {
	// n : 노드의 수, flights : 그래프, src : 출발지, dst : 도착지, k개의 경유지 이내
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
		for (int[] flight : flights) {
			graph.putIfAbsent(flight[0], new HashMap<>());
			graph.get(flight[0]).put(flight[1], flight[2]);
		}

		// 우선순위 큐 생성, 값은 (도착지, 비용, 진행 경로)로 구성하므로, 정렬은 비용(인덱스 1) 기준
		Queue<List<Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.get(1)));

		// 시작은 출발지 src, 비용과 진행 경로는 출발지이므로 모두 0
		pq.add(Arrays.asList(src, 0, 0));

		while (!pq.isEmpty()) {
			List<Integer> cur = pq.poll();
			int u = cur.get(0);
			int price_u = cur.get(1);
			int k_visited = cur.get(2);

			// 큐에서 추출한 값이 도착지 dst라면 정답으로 리턴
			if (u == dst) {
				return price_u;
			}
			// 진행 경로(k_visited)가 남은 경로(k) 이내에 있다면 처리 계속
			if (k_visited <= k) {
				// 진행 경로 +1
				k_visited++;
				// u 지점을 출발지로 한 경로가 있다면 처리 시작
				if (graph.containsKey(u)) {
					// u 지점을 출발지로 한 모든 경로 순회
					for (Map.Entry<Integer, Integer> v : graph.get(u).entrySet()) {
						int alt = price_u + v.getValue();
						pq.add(Arrays.asList(v.getKey(), alt, k_visited));
					}
				}
			}
		}
		// 큐에 모든 값이 비워질 때까지 리턴 못했다면 경로가 존재하지 않는다는 얘기이므로 -1 리턴
		return -1;
	}

	// 더 이상 모든 노드를 결과로 저장할 필요가 없어져서 dist를 제거했다.
	// 하지만, 계산 했던 부분을 계속 계산하는 문제점이 발생
	// 이미 계산했는지 여부와 현재 진행 경로가 기존의 진행 경로보다 작ㅇ느 값인지를 확인한 후 한 번 더 계산하는 형태로 처리
	public int improvedFindCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
		for (int[] flight : flights) {
			graph.putIfAbsent(flight[0], new HashMap<>());
			graph.get(flight[0]).put(flight[1], flight[2]);
		}

		Queue<List<Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.get(1)));

		pq.add(Arrays.asList(src, 0, 0));

		// 타임아웃을 방지하기 위해 한 번 방문한 경로는 저장해두는 맵
		Map<Integer, Integer> visited = new HashMap<>();

		while (!pq.isEmpty()) {
			List<Integer> cur = pq.poll();
			int u = cur.get(0);
			int price_u = cur.get(1);
			int k_visited = cur.get(2);

			if (u == dst) {
				return price_u;
			}

			// 도착지(u)까지의 진행 경로(k_visited) 저장, 따로 저장해두진 않지만 비용(price_u)이 가장 저렴한 경로
			visited.put(u, k_visited);

			if (k_visited <= k) {
				k_visited++;
				if (graph.containsKey(u)) {
					for (Map.Entry<Integer, Integer> v : graph.get(u).entrySet()) {
						// 이미 계산한 경로는 큐에 삽입하지 않는 형태로 풀이를 최적화
						// 아직 계산한 경로가 아니거나, 진행 경로(k_visited)가 기존보다 작다면
						// 도착지(dst)까지는 또 다른 최소 비용을 계산할 가능성이 있으므로 큐에 삽입해 한 번 더 계산을 진행
						if (!visited.containsKey(v.getKey()) || k_visited < visited.get(v.getKey())) {
							int alt = price_u + v.getValue();
							pq.add(Arrays.asList(v.getKey(), alt, k_visited));
						}
					}
				}
			}
		}
		return -1;
	}
}
// https://leetcode.com/problems/cheapest-flights-within-k-stops/description/