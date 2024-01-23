package java_interview.ch12;

public class 섬의_개수 {
	public int numIslands(char[][] grid) {
		int count = 0;
		// 행렬을 모두 검색
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				// 육지일 경우 DFS 시작
				if (grid[i][j] == '1') {
					dfs(i, j, grid);
					count++;
				}
			}
		}
		return count;
	}

	public void dfs(int i, int j, char[][] grid) {
		// 현재 위치가 그리드 밖이거나, 물(0)인 경우 종료
		if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
			return;
		}
		// 한 번만 탐색하기 위해 탐색한 지점은 물(0)로 변경
		grid[i][j] = '0';
		// 동서남북 재귀 DFS
		dfs(i, j + 1, grid);
		dfs(i, j - 1, grid);
		dfs(i + 1, j, grid);
		dfs(i - 1, j, grid);
	}

	/**
	 * 내가 푼 풀이
	 * 육지(1)을 발견하는 순간 메서드를 실행시키는 것은 동일
	 * 하지만 육지의 크기를 행을 기준으로 열을 탐색하는 컨셉으로 진행
	 * 아래와 같은 경우 알고리즘에서 섬을 찾지 못함
	 *  1 1 0 1 1
	 *  1 1 1 1 1
	 *
	 *  ->
	 *  1 1 0 1 1 ㅣ
	 *  1 1 0 0 0 ˇ
	 *
	 * dfs 를 통한 탐색과, 탐색을 완료한 지점을 물(0)로 바꾸는 것이 인상적이였다.
	 */
}
// https://leetcode.com/problems/number-of-islands/description/