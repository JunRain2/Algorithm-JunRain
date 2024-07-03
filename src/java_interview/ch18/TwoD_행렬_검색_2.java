package java_interview.ch18;

import java.util.Arrays;

public class TwoD_행렬_검색_2 {
	public boolean searchMatrix(int[][] matrix, int target) {
		for (int[] a : matrix) {
			if(Arrays.binarySearch(a, target) >= 0)
				return true;
		}
		return false;
	}
}
