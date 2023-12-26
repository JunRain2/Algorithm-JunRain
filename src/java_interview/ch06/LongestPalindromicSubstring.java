package java_interview.ch06;

public class LongestPalindromicSubstring {
	/**
	 * 투 포인터가 팰린드롬을 발견하면 확장하는 형태
	 * 판별만 하면 되기 떄문에, 중앙을 중심으로 점점 확장
	 */
	static int left;
	static int maxLen;

	public static void extendPalindrome(String s, int j, int k) {
		// 투 포인터가 유효한 범위 내에 있고 양쪽 끝 문자가 일치하는 팰린드롬인 경우 범위 확장
		while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
			j--;
			k++;
		}

		// 기존 최대 길이보다 클 경우 값 교체
		if (maxLen < k - j - 1) {
			left = j + 1;
			maxLen = k - j - 1;
		}
	}

	public static String longestPalindrome(String s) {
		int len = s.length();

		// 문자열 길이가 1일 경우 그대로 리턴
		if(len < 2)
			return s;

		// 2칸 짜리 투 포인터, 3칸짜리 투 포인터
		// 결국은 모든 문자열을 흝음
		for (int i = 0; i < len; i++) {
			extendPalindrome(s, i, i + 1); // 2칸 짜리
			extendPalindrome(s, i, i + 2); // 3칸 짜리
		}

		return s.substring(left, left + maxLen);
	}

	public static void main(String[] args) {
		System.out.println(longestPalindrome("dcbabcdd"));
	}
}

/**
 * 2칸짜리 투 포인터와 3칸짜리 투 포인터는 팰린드롬을 찾을 때 까지 계속 이동
 * 팰린드롬을 찾으면 멈춰서 윈도우의 크기를 가운데를 기준으로 확장, 즉 좌우로 커진다.
 * 팰린드롬이 짝수일 경우와 홀수일 경우가 있기 때문에 2칸, 3칸으로 나눔.
 */