package java_interview.ch11;

import java.util.HashMap;

public class 완주하지_못한_선수 {
	public String solution(String[] participant, String[] completion) {
		String answer = "";
		HashMap<String, Integer> map = new HashMap<>();

		for (String s : participant) {
			map.put(s, map.getOrDefault(s, 0) + 1);
		}

		for (String s : completion) {
			map.put(s, map.get(s) - 1);

			if (map.get(s) == 0) {
				map.remove(s);
			}
		}

		String[] s = map.keySet().toArray(new String[0]);
		answer = s[0];

		return answer;
	}
}
//https://school.programmers.co.kr/learn/courses/30/lessons/42576
