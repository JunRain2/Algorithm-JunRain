package java_interview.ch17;

import java.util.Arrays;

public class 유효한_애너그램 {
	public boolean isAnagram(String s, String t) {
		char[] sChar = s.toCharArray();
		Arrays.sort(sChar);

		char[] tChar = t.toCharArray();
		Arrays.sort(tChar);

		return Arrays.equals(sChar, tChar);
	}
}
