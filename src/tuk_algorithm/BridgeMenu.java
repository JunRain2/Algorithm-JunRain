package tuk_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Graph {
	private int vertices;
	private Map<Integer, List<Integer>> adjList;
	private int time;

	public Graph(int vertices) {
		this.vertices = vertices;
		this.adjList = new HashMap<>();

		for (int i = 0; i < vertices; i++) {
			adjList.put(i, new LinkedList<>());
		}
	}

	public void addEdge(int u, int v) {
		adjList.get(u).add(v);
		adjList.get(v).add(u);
	}

	private void bridgeUtil(int u, boolean[] visited, int[] disc, int[] low, int[] parent, List<int[]> result) {
		visited[u] = true;
		disc[u] = low[u] = ++time;

		for (int v : adjList.get(u)) {
			if (!visited[v]) {
				parent[v] = u;
				bridgeUtil(v, visited, disc, low, parent, result);
				low[u] = Math.min(low[u], low[v]);

				if (low[v] > disc[u]) {
					result.add(new int[] {u, v});
				}
			} else if (v != parent[u]) {
				low[u] = Math.min(low[u], disc[v]);
			}
		}
	}

	public List<int[]> findBridges() {
		boolean[] visited = new boolean[vertices];
		int[] disc = new int[vertices];
		int[] low = new int[vertices];
		int[] parent = new int[vertices];
		List<int[]> result = new ArrayList<>();
		time = 0;

		for (int i = 0; i < vertices; i++) {
			if (!visited[i]) {
				bridgeUtil(i, visited, disc, low, parent, result);
			}
		}

		return result;
	}

	public boolean isBridge(int u, int v) {
		List<Integer> adjU = adjList.get(u);
		List<Integer> adjV = adjList.get(v);

		adjU.remove(Integer.valueOf(v));
		adjV.remove(Integer.valueOf(u));

		boolean[] visited = new boolean[vertices];
		int[] disc = new int[vertices];
		int[] low = new int[vertices];
		int[] parent = new int[vertices];
		time = 0;

		bridgeUtil(0, visited, disc, low, parent, new ArrayList<>());

		adjU.add(v);
		adjV.add(u);

		for (int i = 0; i < vertices; i++) {
			if (!visited[i]) {
				return true;
			}
		}

		return false;
	}
}

public class BridgeMenu {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("정점 개수를 입력하세요: ");
		int vertices = scanner.nextInt();
		Graph graph = new Graph(vertices);

		while (true) {
			System.out.print("간선의 양 끝점을 입력하세요: ");
			int u = scanner.nextInt();
			int v = scanner.nextInt();

			if (u == -1 && v == -1) {
				break;
			}

			graph.addEdge(u, v);
		}

		while (true) {
			System.out.println("\n메뉴를 선택하세요:");
			System.out.println("1. 전체 브리지 찾기");
			System.out.println("2. 특정 간선이 브리지인지 판별");
			System.out.println("3. 종료");

			int choice = scanner.nextInt();

			switch (choice) {
				case 1:
					List<int[]> bridges = graph.findBridges();
					if (!bridges.isEmpty()) {
						System.out.println("다음은 브리지입니다:");
						for (int[] bridge : bridges) {
							System.out.println(Arrays.toString(bridge));
						}
					} else {
						System.out.println("브리지가 없습니다.");
					}
					break;
				case 2:
					System.out.print("브리지 여부를 확인할 간선의 양 끝점을 입력하세요: ");
					int u = scanner.nextInt();
					int v = scanner.nextInt();
					boolean isBridge = graph.isBridge(u, v);
					System.out.println("입력한 간선은 브리지인가요? " + isBridge);
					break;
				case 3:
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
				default:
					System.out.println("올바른 메뉴를 선택하세요.");
			}
		}
	}
}
