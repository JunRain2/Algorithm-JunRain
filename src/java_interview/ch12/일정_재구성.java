package java_interview.ch12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class 일정_재구성 {
	public List<String> findItinerary(List<List<String>> tickets) {
		List<String> results = new ArrayList<>();

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
}
// https://leetcode.com/problems/reconstruct-itinerary/