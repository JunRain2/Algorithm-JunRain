package pure_java;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 부동소수점이라 근사값을 표시하는 double에 문제점을 보완하기 위해 사용
 * BigDecimal : 불변의 성질을 띠며, 임의 정밀도와 부호를 지니는 10진수
 * 즉 근사값을 가진 double의 문제점을 해결하고 정확한 값을 가진다.
 * 근사값이 아닌 정확한 수가 필요한 돈문제 같은 곳에서 사용
 * https://cheony-y.tistory.com/334
 */
public class BigDecimalMain {
	public static void main(String[] args) {
		// 생성자
		BigDecimal methodDecimal = BigDecimal.valueOf(1234.1234);
		BigDecimal stringDecimal = new BigDecimal("1234.12340");
		BigDecimal doubleDecimal = new BigDecimal(1234.1234); // 근사값인 double이 들어가기 때문에 정확한 값을 만들어내지 못한다.

		// 일부 메서드
		BigInteger bigIntVal = methodDecimal.unscaledValue(); // 소수점을 없애고, 모든 수를 정수로 표시 -> BigInteger로 반환
		int intVal = bigIntVal.intValue(); // int로 반환
		int scale = methodDecimal.scale(); // 소수점 첫쨰 자리 ~ 0 이 아닌수로 끝나는 수 까지의 소수점 자리
		int precision = methodDecimal.precision(); // 전체 자리수 개수

		// 사칙 연산 -> 인수로 BigDecimal을 넣어야 한다.
		methodDecimal.add(BigDecimal.TEN); // 더하기
		methodDecimal.subtract(BigDecimal.TEN); // 빼기
		methodDecimal.multiply(BigDecimal.TEN); // 곱하기
		methodDecimal.divide(BigDecimal.TEN); // 나누기

		// 비교 연산
		System.out.println(methodDecimal.equals(stringDecimal)); // value와 scale을 모두 비교해 false
		System.out.println(methodDecimal.compareTo(stringDecimal)); // 소수점 맨 끝의 0을 무시하고 값만을 비교 따라서 true -> 0


	}
}
