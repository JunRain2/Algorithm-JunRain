package java_interview.ch05;

import java.util.Scanner;

public class Palindrome {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		System.out.println(isPalindrome(s));
	}

	public static boolean isPalindrome(String s) {
		String sFiltered = s.toLowerCase().replaceAll("[^a-z0-9]", "");
		String sReversed = new StringBuilder(sFiltered).reverse().toString();
		return sFiltered.equals(sReversed);
	}

	public static boolean isPalindromeT(String s) {
		int start = 0;
		int end = s.length() - 1;

		while (start < end) {
			if (!Character.isLetterOrDigit(s.charAt(start))) {
				start++;
			} else if (!Character.isLetterOrDigit(s.charAt(end))) {
				end--;
			} else {
				if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
					return false;
				}
				start++;
				end--;
			}
		}
		return true;
	}
}
