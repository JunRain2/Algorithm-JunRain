package java_interview.ch21;

import java.util.Arrays;

public class 쿠키_부여 {
	public int findContentChildren(int[] g, int[] s) {
		// 그리디하게 처리하기 위해 입력값 모두 정렬
		Arrays.sort(g);
		Arrays.sort(s);

		int i = 0; // 어린이를 가리키는 인덱스
		int j = 0; // 쿠키를 가리키는 인덱스

		// 어린이 인덱스와 쿠키 인덱스가 각각 전체 길이보다 작을 때 반복
		while (i < g.length && j < s.length) {
			// 어린이가 원하는 크기보다 쿠키가 더 클 때만 조건이 만족되고 다음 어린이로 이동
			if (s[j] >= g[i]) {
				i++;
			}
			j++; // 다음 쿠키로 이동
		}
		return i;
	}
}
