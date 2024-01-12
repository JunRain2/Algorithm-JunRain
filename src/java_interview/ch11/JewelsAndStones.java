package java_interview.ch11;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class JewelsAndStones {
	// 내가 푼 문제
	public int numJewelsInStones(String jewels, String stones) {
		HashMap<Character, Integer> map = new HashMap<>();
		int result = 0;

		for (char c : jewels.toCharArray()) {
			map.put(c, 0);
		}

		for (char c : stones.toCharArray()) {
			if (map.containsKey(c)) {
				result++;
			}
		}
		return result;
	}

	public int numJewelsInStonesHashMap(String jewels, String stones) {
		int count = 0;
		HashMap<Character, Integer> freqs = new HashMap<>();

		// 돌(S)의 빈도수 개산
		for (char c : stones.toCharArray()) {
			if (freqs.containsKey(c)) {
				freqs.put(c, freqs.get(c) + 1);
			} else {
				freqs.put(c, 1);
			}
		}

		for (char j : jewels.toCharArray()) {
			if (freqs.containsKey(j)) {
				count += freqs.get(j);
			}
		}

		return count;
	}

	// 내가 푼 풀이와 비슷, 즉 나는 Set으로 풀어야 하는 문제를 Map으로 푼 것.
	public int numJewelsInStonesHashSet(String jewels, String stones) {
		int count = 0;
		Set<Character> freqs = new HashSet<>();

		for (char c : jewels.toCharArray()) {
			freqs.add(c);
		}

		for (char c : stones.toCharArray()) {
			if (freqs.contains(c)) {
				count++;
			}
		}
		return count;
	}
}
// https://leetcode.com/problems/jewels-and-stones/