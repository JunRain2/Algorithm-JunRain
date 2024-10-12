package java_interview.ch19;

public class 두_정수의_합 {
	public int getSum(int a, int b) {
		// b가 0이 될 때까지 진행
		while (b != 0) {
			// 자릿수를 올려가며 carry 값 처리
			int carry = (a & b) << 1;
			// cary 값을 고려하지 않는 a와 b의 합(sum)
			a = a ^ b;
			// carry 값을 b에 할당
			b = carry;
		}
		return a;
	}
}
