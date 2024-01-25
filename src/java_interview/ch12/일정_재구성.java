package java_interview.ch12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class 일정_재구성 {
	public List<String> findItinerary(List<List<String>> tickets) {
		List<String> results = new ArrayList<>();

		// 출발지가 중복될 경우, 사전순으로 도착해야하기 때문에, 도착지 기준으로 정렬
		Collections.sort(tickets, Comparator.comparing(o -> o.get(1)));

		dfs(tickets, "JFK", results);

		return results;
	}

	// case 1 만족
	// case 2 만족
	// [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]] 불만족
	public void dfs(List<List<String>> tickets, String from, List<String> results) {
		results.add(from);

		for (List<String> t : new ArrayList<>(tickets)) {
			if (t.get(0).equals(from)) {
				String to = t.get(1);
				tickets.remove(t);
				dfs(tickets, to, results);
				return;
			}
		}
	}
	/**
	 * 내가 푼 풀이
	 */

	public List<String> findItineraryBook(List<List<String>> tickets) {
		List<String> results = new LinkedList<>();
		Map<String, PriorityQueue<String>> fromToMap = new HashMap<>();

		// 여행 일정을 from -> to 형태의 그래프로 구성
		for (List<String> ticket : tickets) {
			// 값이 존재하지 않을 경우 빈 우선순위 큐 생성 -> 키가 존재하면 아무런 일을 하지 않는다.
			fromToMap.putIfAbsent(ticket.get(0), new PriorityQueue<>());
			// 목적지 to 추가, 우선순위 큐 이므로 to는 항상 사전 어휘순으로 정렬된다.
			fromToMap.get(ticket.get(0)).add(ticket.get(1));
		}

		dfsBook(results, fromToMap, "JFK");

		return results;
	}

	public void dfsBook(List<String> results, Map<String, PriorityQueue<String>> fromToMap, String from) {
		// from -> to 값이 존재하는 경우 반복해서 재귀 DFS
		while (fromToMap.containsKey(from) && !fromToMap.get(from).isEmpty()) {
			// 사전 어휘순 첫 위치부터 우선순위 큐를 이용해 추출 및 재귀 DFS 진행
			dfsBook(results, fromToMap, fromToMap.get(from).poll());
		}
		// 재귀 DFS가 모두 끝났다면 최종 위치는 도착지이므로 결과를 출발지까지 앞으로 삽입한다.
		results.add(0, from);
	}

	/**
	 * 책이 푼 문제
	 */
}
// https://leetcode.com/problems/reconstruct-itinerary/