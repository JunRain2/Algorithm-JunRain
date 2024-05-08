package java_interview.ch13;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// 동서남북으로 DFS를 통해서 탐색하면서 다익스트라 알고리즘을 구현
// 바둑판 모양의 맵을 탐색하는 문제 -> 35번 문제
public class 게임_맵_최단_거리 {
	// 다익스트라 알고리즘을 위한 거리순 우선순위 큐 선언
	Queue<Position> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.distance));

	/**
	 * 좌표 x,y가 맵 안에 위치한 유효한 경로고 0이 아니라면, 즉 지나갈 수 있는 경로라면 해당 위치를 0으로 설정해서
	 * 더 이상 못지나가도록 함
	 * 현재 위치를 우선순위 큐에 삽입해 다음번 최단 거리를 구할 때 추출될 수 있게 한다.
	 */
	public void findPath(int y, int x, int distance, int[][] maps) {
		if (y >= 0 && y < maps.length && x >= 0 && x < maps[0].length && maps[y][x] != 0) {
			// 이미 지나온 경로는 더 이상 거치지 않도록 0으로 설정
			maps[y][x] = 0;
			// 현재 위치와 거리+1을 우선순위 큐에 삽입
			pq.add(new Position(y, x, distance + 1));
		}
	}

	public int solution(int[][] maps) {
		// 출발지부터 삽입
		pq.add(new Position(0, 0, 1));
		// 해당 지점까지의 거리 결과를 저장하는 변수 선언
		Map<Integer, Position> dist = new HashMap<>();

		while (!pq.isEmpty()) {
			Position cur = pq.poll();

			// 아직 계산하지 않은 지점이라면 계산 결과 삽입
			// 키/값 형태이므로 키는 (y*1000+x)로 구성
			if (!dist.containsKey(cur.y * 1000 + cur.x)) {
				dist.put(cur.y * 1000 + cur.x, cur);

				// 즉, 거리가 제일 짧은 값의 동서남북만 DFS를 통해서 탐색
				findPath(cur.y, cur.x + 1, cur.distance, maps); // 동
				findPath(cur.y, cur.x - 1, cur.distance, maps); // 서
				findPath(cur.y + 1, cur.x, cur.distance, maps); // 남
				findPath(cur.y - 1, cur.x, cur.distance, maps); // 북
			}
		}

		// 마지막 위치의 결과가 있다면 해당 위치의 거리를 정답으로 리턴
		if (dist.containsKey((maps.length - 1) * 1000 + (maps[0].length - 1))) {
			return dist.get(((maps.length - 1) * 1000) + (maps[0].length - 1)).distance;
		}

		return -1;
	}

	// 위치정보
	static class Position {
		final int y;
		final int x;
		final int distance;

		public Position(int y, int x, int distance) {
			this.y = y;
			this.x = x;
			this.distance = distance;
		}
	}
}
