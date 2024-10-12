package java_interview.ch19;

public class 해밍_거리 {
	public int hammingDistance(int x, int y) {
		return Integer.bitCount(x ^ y);
	}
}
