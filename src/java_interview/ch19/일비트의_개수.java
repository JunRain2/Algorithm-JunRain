package java_interview.ch19;

public class 일비트의_개수 {
	public int hammingWeight(int n) {
		char[] bits = Integer.toBinaryString(n).toCharArray();

		int count = 0;
		for (char bit : bits) {
			if(bit=='1') count++;
		}

		return count;
	}
}
