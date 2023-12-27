package java_interview.ch07;

import java.util.ArrayDeque;
import java.util.Deque;

public class TrappingRainWater {

	/**
	 * 제일 높은 막대는, 얼마나 높은지 부피와 관계 없고 그저 오른쪽과 왼쪽을 나누는 역할을 한다. -> 이 문제의 포인트
	 * 최대 높이의 막대까지 각각 좌우 막대 최대 높이 leftMax, rightMax가 현재 높이와의 차이만큼 물 높이 volume를 더해나간다.
	 * 낮은 쪽은 그만큼 항상 채워질 것이기 때문에, 좌우 어느 쪽이든 낮은 쪽은 높은 쪽을 향해서 포인터가 가운데로 점점 이동
	 *
	 * 제일 높은 막대기 부분에서 만나게 하는 것이 목표 -> 내가 걱정하는 물이 고이는 웅덩이의 부피 문제를 해결
	 */
	public static int trapTwoPoint(int[] height) {
		int volume = 0;
		int left = 0;
		int right = height.length - 1;
		int leftMax = height[left];
		int rightMax = height[right];

		// 겹칠때 까지 반복
		while (left < right) {
			// 포인터가 지나왔던 것 중 최대 높이 -> volume에 음수가 올 수 없다.
			leftMax = Math.max(height[left], leftMax);
			rightMax = Math.max(height[right], rightMax);

			// 더 높은 쪽을 향해 투포인터 이동
			if (leftMax <= rightMax) {
				// 왼쪽 막대 최대 높이와의 차이를 더하고 왼쪽 포인터 이동
				volume += leftMax - height[left];
				left += 1;
			} else {
				// 오른쪽 막대 최대 높이와의 차이를 더하고 오른쪽 포인터 이동
				volume += rightMax - height[right];
				right -= 1;
			}
		}
		return volume;
	}

	/**
	 * 스택에 쌓아나가면서 현재 높이가 이전 높이보다 높을 때, 즉 꺽이는 부분 변곡점을 기준으로 격차만큼 물이 쌓이는 양 volume을 채운다.
	 * 이전 높이는 고정된 형태가 아니라 들쑥날쑥하기 때문에, 계속 스택으로 채워나가다가 변곡점을 만날 때마다 스택에서 하나씩 꺼내면서 이전과의 차이만큼
	 * 물이 쌓이는 양을 채워 나간다.
	 */
	public static int trapStack(int[] height) {
		// 자바에서의 Stack은 문제가 많기 때문에 ArrayDeque를 대신 사용
		// 스택에 index 즉 위치를 저장
		Deque<Integer> stack = new ArrayDeque<>();
		int volume = 0;

		for (int i = 0; i < height.length; i++) {
			// 변곡점을 만나는 경우
			while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
				// 스택에서 꺼낸다.
				Integer top = stack.pop();

				if(stack.isEmpty())
					break;

				// 스택의 마지막 위치까지를 거리로 계산
				int distance = i - stack.peek() - 1;
				// 현재 높이 또는 스택의 마지막 위치 높이 중 낮은 값에 방금 꺼낸 높이의 차이를 물 높이로 지정
				int waters = Math.min(height[i], height[stack.peek()]) - height[top];

				// 물이 쌓이는 양은 거리와 물 높이의 곱
				volume += distance * waters;
			}

			// 진행하면서 현재 위치를 스택에 삽입
			stack.push(i);
		}

		return volume;
	}

	public static void main(String[] args) {
		int[] height = new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
		System.out.println(trapTwoPoint(height));
		System.out.println(trapStack(height));

	}
}
