package baek;

import java.io.*;
import java.util.*;

public class 쿼드트리 {
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] quadTree = new int[n][n];

		for (int i = 0; i < n; i++) {
			String[] strArr = br.readLine().split("");
			for (int j = 0; j < n; j++) {
				quadTree[i][j] = Integer.parseInt(strArr[j]);
			}
		}

		System.out.println(dfs(0, n, 0, n, quadTree));
	}

	static String dfs(int horizontalStart, int horizontalEnd, int verticalStart, int verticalEnd, int[][] quadTree) {
		if(horizontalStart == horizontalEnd && verticalStart == verticalEnd) {
			return String.valueOf(quadTree[verticalStart][horizontalStart]);
		}
		StringBuilder sb = new StringBuilder();
		int horizontalMid = horizontalStart + (horizontalEnd - horizontalStart) / 2;
		int verticalMid = verticalStart + (verticalEnd - verticalStart) / 2;

		final int flag = quadTree[verticalStart][horizontalStart];

		for (int i = verticalStart; i < verticalEnd; i++) {
			for (int j = horizontalStart; j < horizontalEnd; j++) {
				if (flag != quadTree[i][j]) {
					sb.append("(");
					sb.append(dfs(horizontalStart, horizontalMid, verticalStart, verticalMid, quadTree)); // 왼쪽 위
					sb.append(dfs(horizontalMid, horizontalEnd, verticalStart, verticalMid, quadTree)); // 오른쪽 위
					sb.append(dfs(horizontalStart, horizontalMid, verticalMid, verticalEnd, quadTree)); // 왼쪽 아래
					sb.append(dfs(horizontalMid, horizontalEnd, verticalMid, verticalEnd, quadTree)); // 오른쪽 아래
					sb.append(")");

					return sb.toString();
				}
			}
		}
		return String.valueOf(flag);
	}
}
