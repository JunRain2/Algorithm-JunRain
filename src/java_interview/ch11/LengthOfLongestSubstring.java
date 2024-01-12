package java_interview.ch11;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LengthOfLongestSubstring {
	// 내가 푼 풀이, 시간이 오래 걸림 97ms 2중 for문을 사용했기 때문
	public int lengthOfLongestSubstring(String s) {
		int result = 0;
		char[] chars = s.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			Set<Character> set = new HashSet<>();
			int count = 0;
			for (int j = i; j < chars.length; j++) {
				if (!set.contains(chars[j])) {
					set.add(chars[j]);
					count++;
				} else {
					break;
				}

				if (count > result) {
					result = count;
				}
			}
		}

		return result;
	}

	// 슬라이딩 윈도우와 투 포인터로 크기 조절, 5ms로 아주 빠른 속도
	public int lengthOfLongestSubstringTwoPointer(String s) {
		Map<Character, Integer> used = new HashMap<>();
		int maxLength = 0;
		int left = 0;
		int right = 0;
		// 문자열을 문자 단위로 반복
		for (char c : s.toCharArray()) {
			// 이미 등장했던 문자이고, 슬라이딩 윈도우의 안쪽에 있다면 left 위치 업데이트
			if (used.containsKey(c) && left <= used.get(c)) {
				left = used.get(c) + 1;
			} else {
				// 최대 부분 문자열 길이 업데이트
				maxLength = Math.max(maxLength, right - left + 1);
			}

			// 현재 문자의 위치 삽입
			used.put(c, right);
			// 오른쪽 포인터 right는 현재 문자의 위치에 맞춰 계속 증가
			right++;
		}
		return maxLength;
	}
}
// https://leetcode.com/problems/longest-substring-without-repeating-characters/