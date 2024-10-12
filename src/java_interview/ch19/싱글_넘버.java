package java_interview.ch19;

public class 싱글_넘버 {
	/**
	 * @param nums 2개씩 중복된 수로 된 배열 중 중복되지 않은 수가 존재
	 * @return 중복되지 않은 수
	 */
	public int singleNumber(int[] nums) {
		int result = 0;
		for (int num : nums) {
			result ^= num;
		}
		return result;
	}
}
