package java_interview.ch06;

import java.util.Arrays;
import java.util.Scanner;

public class ReverseString {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		reverseString(s.toCharArray());
	}
	public static void reverseString(char[] s) {
		int start = 0;
		int end = s.length - 1;
		while (start < end) {
			char temp = s[start];
			s[start] = s[end];
			s[end] = temp;

			start++;
			end--;
		}
	}

	public static void reverseString1(char[] s) {
		String s1 = s.toString();
		// toCharArray 에서 이상하게 잘리는 문제
		// 문자열 배열 -> 문자 -> 문자열 배열 과정에서 문제가 생김
		s = new StringBuilder(s1).reverse().toString().toCharArray();
		System.out.println(Arrays.toString(s));
	}
}
