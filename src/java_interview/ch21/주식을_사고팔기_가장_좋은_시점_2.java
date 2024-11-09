package java_interview.ch21;

public class 주식을_사고팔기_가장_좋은_시점_2 {
	public int maxProfit(int[] prices) {

		int result = 0;
		int before = prices[0];

		for (int price : prices) {
			if (price > before) {
				result += price - before;
			}

			before = price;
		}

		return result;
	}
}
