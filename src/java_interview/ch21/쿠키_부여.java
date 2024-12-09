package java_interview.ch21;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class 쿠키_부여 {
	public int findContentChildren(int[] g, int[] s) {
		int result = 0;

		// 어린아이들과 쿠키를 역순으로 정렬
		Integer[] children = Arrays.stream(g).boxed().toArray(Integer[]::new);
		Arrays.sort(children, Collections.reverseOrder());
		Integer[] cookies = Arrays.stream(s).boxed().toArray(Integer[]::new);
		Arrays.sort(cookies, Comparator.reverseOrder());

		int i = 0;
		for (Integer c : cookies) {
			for (; i < children.length; i++) {
				if (c >= children[i]) {
					result++;
				}
			}
		}

		return result;
	}
}
