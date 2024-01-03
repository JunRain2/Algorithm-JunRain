package java_interview.ch06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
	// 정렬하여 비교 하는 것이 제일 쉽다.
	static public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> words = new HashMap<>();

		List<List<String>> result = new ArrayList<>();

		for (String s : strs) {
			char[] chars = s.toCharArray();
			Arrays.sort(chars);
			// 배열을 그냥 toString 하면 주소값이 나옴 -> String.valueOf를 사용
			String sortedWord = String.valueOf(chars);

			List<String> w = words.getOrDefault(sortedWord, new ArrayList<>());
			w.add(s);

			words.put(sortedWord, w);
		}

		for (List<String> list : words.values()) {
			result.add(list);
		}

		return result;
	}

	public static void main(String[] args) {
		String[] strs = List.of("eat", "tea", "tan", "ate", "nat", "bat").toArray(new String[0]);
		System.out.println(groupAnagrams(strs));

	}
}
// https://leetcode.com/problems/group-anagrams