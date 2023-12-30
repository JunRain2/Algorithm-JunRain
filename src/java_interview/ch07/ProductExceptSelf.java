package java_interview.ch07;

import java.util.ArrayList;
import java.util.List;

/**
 * 나눗셈 이용 금지 -> 배열 각 인덱스에 전체 곱을 넣은 후 각 인덱스로 나누는 방법 금지
 * 자기자신을 제외하고 왼쪽의 곱셈 결과와 오른쪽의 곱셈 결과를 곱하는 방법
 * 왼 -> 오 : 곱셈을 쌓아서 감
 * 오 -> 왼 : 왼 -> 오 에서 쌓이지 못한 곱셈을 쌓아줌
 */
public class ProductExceptSelf {
	public int[] productExceptSelf(int[] nums) {
		int[] result = new int[nums.length];

		// 왼쪽 곱셈
		int p = 1;
		for (int i = 0; i < nums.length; i++) {
			// 대입을 먼저해서 자기자신을 제외시킴
			result[i] = p;
			p *= nums[i];
		}

		// 오른쪽 곱셈
		p = 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			result[i] *= p;
			p *= nums[i];
		}
		return result;
	}
}
