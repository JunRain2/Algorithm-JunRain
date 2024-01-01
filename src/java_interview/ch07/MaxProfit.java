package java_interview.ch07;

public class MaxProfit {
	// 브루트 포스로 계산
	// 타임아웃으로 문제가 풀리지 않음
	public int maxProfitForce(int[] prices) {
		int max = 0;
		for (int i = 0; i < prices.length; i++) {
			for (int j = i + 1; j < prices.length; j++) {
				max = Math.max(max, prices[j] - prices[i]);
			}
		}
		return max;
	}

	public int maxProfit(int[] prices) {
		int maxProfit = 0, minPrice = prices[0];
		for (int price : prices) {
			minPrice = Math.min(price, minPrice);
			// 현재 값과 이전 저점들과 비교
			maxProfit = Math.max(maxProfit, price - minPrice);
		}

		return maxProfit;
	}
}

