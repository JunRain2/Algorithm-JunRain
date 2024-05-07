package java_interview.ch13;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class k_경유지_내_가장_저렴한_항공권 {
	// n : 노드의 수, flights : 그래프, src : 출발지, dst : 도착지, k개의 경유지 이내
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
		
	}

	public int networkDelayTime(int[][] times, int n, int k) {
		Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
		for (int[] time : times) {
			graph.putIfAbsent(time[0], new HashMap<>());
			graph.get(time[0]).put(time[1], time[2]);
		}

		Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());
		pq.add(new AbstractMap.SimpleEntry<>(k, 0));
		Map<Integer, Integer> dist = new HashMap<>();
		while (!pq.isEmpty()) {
			Map.Entry<Integer, Integer> cur = pq.poll();
			int u = cur.getKey();
			int dist_u = cur.getValue();

			if(!dist.containsKey(u)) {
				dist.put(u, dist_u);
				if(graph.containsKey(u)) {
				for(Map.Entry<Integer, Integer> v : graph.get(u).entrySet()) {
					int alt = dist_u + v.getValue();
					pq.add(new AbstractMap.SimpleEntry<>(v.getKey(), alt));
				}
			}
		}
	}
		if (dist.size() == n) {
			return Collections.max(dist.values());
		}
		return -1;
		}


}
// https://leetcode.com/problems/cheapest-flights-within-k-stops/description/